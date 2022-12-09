package com.fiuni.administrador.service.rol;

import com.fiuni.administrador.dao.rol.IRolDao;
import com.fiuni.administrador.dto.rol.RolDto;
import com.fiuni.administrador.dto.rol.RolResult;
import com.fiuni.administrador.service.base.BaseServiceImpl;
import com.fiuni.administrador.utils.Settings;
import com.library.domainLibrary.domain.rol.RolDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class RolServiceImple extends BaseServiceImpl <RolDto ,RolDomain ,RolResult > implements IRolService {

    @Autowired
    private IRolDao rolDao;

    @Autowired
    private CacheManager cacheManager;

    private  RolDomain rolDomain;




    @Override
    @Transactional
    protected RolDto convertDomainToDto(RolDomain domain) {
        RolDto dto = new RolDto();
        dto.setId(domain.getId());
        dto.setNombre(domain.getNombre());
        dto.setEstado(domain.getEstado());
        return dto;
    }

    @Override
    protected RolDomain convertDtoToDomain(RolDto dto) {
        RolDomain dom = new RolDomain();
        dom.setId(dto.getId());
        dom.setNombre(dto.getNombre());
        dom.setEstado(dto.getEstado());
        return dom;
    }

    @Override

  //  @CachePut()
    public RolDto save(RolDto dto) {
        dto.setEstado(dto.getEstado() == null ? true : dto.getEstado());
        RolDto response = convertDomainToDto(rolDao.save(convertDtoToDomain(dto)));


        if(dto.getId() == null){
            cacheManager.getCache(Settings.CACHE_NAME).put("API_ROL_" + response.getId(), response);
        }

        return response;


    }

    @Override
    @Transactional

    //@Cacheable(value, key ="'api_rol_'+#aafs")
    @Cacheable(value = Settings.CACHE_NAME, key="'API_ROL_' + #id")
    public RolDto getById(Integer id) {
        Optional<RolDomain> rolDomainOp = rolDao.findById(id);
        RolDto response = rolDomainOp.map(rol -> {
            return convertDomainToDto(rol);
        }).orElse(null);

        return response;
    }


    @Override
    @Transactional
    public RolResult getAll(Pageable pageable) {
        RolResult result = new RolResult(rolDao.findAll(pageable).map(rol -> {
            RolDto dto= convertDomainToDto(rol);

            cacheManager.getCache(Settings.CACHE_NAME).putIfAbsent("api_rol_" + dto.getId(), dto);
            return dto;

        }).toList());

        return result;
    }


    @Override
    @Transactional
    public RolDto update(Integer id, RolDto dto) {
        if (dto.getEstado() != null && dto.getNombre() != null) {
            RolDto rolActualizada = rolDao.findById(id).map(rolDomain -> {
                rolDomain.setNombre(dto.getNombre());
                rolDomain.setEstado(dto.getEstado());
                dto.setId(rolDomain.getId());
                cacheManager.getCache(Settings.CACHE_NAME).evictIfPresent("API_ROL_" + id);
                return save(dto);
            }).orElse(null);
            if(rolActualizada != null){
                cacheManager.getCache(Settings.CACHE_NAME).put("API_ROL_" + rolActualizada.getId(), rolActualizada);
            }
            return rolActualizada;
        }
        return null;

    }



    @Override
    @Transactional


   //@CacheEvict(value =Settings.CACHE_NAME,allEntries=true)
    public ResponseEntity<Boolean> delete(Integer id) {
        Boolean response = rolDao.findById(id).map(rolDomain -> {
            RolDto dto = convertDomainToDto(rolDomain);
            if (dto.getEstado()) {
                dto.setEstado(false);
                save(dto);
                cacheManager.getCache(Settings.CACHE_NAME).evictIfPresent("API_ROL_" + id);
                return true;
            } else {
                return false;
            }
        }).orElse(null);
        return new ResponseEntity<Boolean>(response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }



    @Override
    @Transactional
    public Integer deleteAbs(Integer id) {
        Integer response = rolDao.deleteAbsolut(id);
        if(response != null && response > 0){
            cacheManager.getCache(Settings.CACHE_NAME).evictIfPresent("API_ROL_" + id);
        }
        return response;
    }


}


