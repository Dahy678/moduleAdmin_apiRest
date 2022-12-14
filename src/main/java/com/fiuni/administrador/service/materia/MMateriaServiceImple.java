package com.fiuni.administrador.service.materia;

import com.fiuni.administrador.dao.materia.IMateriaDao;
import com.fiuni.administrador.dto.materias.MateriaDTO;
import com.fiuni.administrador.dto.materias.MateriaResult;
import com.fiuni.administrador.service.base.BaseServiceImpl;
import com.fiuni.administrador.utils.Settings;
import com.library.domainLibrary.domain.materia.MateriaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MMateriaServiceImple extends BaseServiceImpl<MateriaDTO, MateriaDomain, MateriaResult> implements IMMaterialService {


    @PersistenceContext
    private EntityManager em;
    @Autowired
    private IMateriaDao materiaDao;



    private MateriaDomain materiaDomain;

    @Autowired
    private CacheManager cacheManager;


    @Override
    @Transactional
    protected MateriaDTO convertDomainToDto(MateriaDomain domain) {

        MateriaDTO dto= new MateriaDTO();

        dto.setId(domain.getId());
        dto.setNombre(domain.getNombre());
        dto.setEstado(domain.getEstado());
        return dto;
    }

    @Override
    @Transactional
    protected MateriaDomain convertDtoToDomain(MateriaDTO dto) {
       MateriaDomain dm = new MateriaDomain();
       dm.setId(dto.getId());
       dm.setNombre(dto.getNombre());
       dm.setEstado(dto.getEstado());

       return dm;
    }

    @Override
    @Transactional
    public MateriaDTO save(MateriaDTO dto) {
        dto.setEstado(dto.getEstado() == null ? true : dto.getEstado());
        MateriaDTO response = convertDomainToDto(materiaDao.save(convertDtoToDomain(dto)));

        if(dto.getId() == null){
            cacheManager.getCache(Settings.CACHE_NAME).put("API_MATERIA_" + response.getId(), response);
        }

        return response;
    }

    /*
       public EtapaDTO save(EtapaDTO dto) {
        dto.setEstado(dto.getEstado() == null ? true : dto.getEstado());
        EtapaDomain domain = etapaDao.save(convertDtoToDomain(dto));
        EtapaDTO response = convertDomainToDto(domain);
        if(dto.getId() == null){
            cacheManager.getCache(Settings.CACHE_NAME).put("API_ETAPA_" + response.getId(), response);
        }

        return response;
    }
	}*/

    @Override
    @Transactional
    public MateriaDTO getById(Integer id) {
        Optional<MateriaDomain> materiaDomain = materiaDao.findById(id);
        MateriaDTO response = materiaDomain.map(m -> {
            return convertDomainToDto(m);
        }).orElse(null);

       return response;
    }



    @Override
    @Transactional
    public MateriaResult getAll(Pageable pageable) {

        Page<MateriaDomain> page = materiaDao.getByEstadoTrue(pageable);


        MateriaResult response = new MateriaResult(page.map(materia -> {
            MateriaDTO dto = convertDomainToDto(materia);
            cacheManager.getCache(Settings.CACHE_NAME).putIfAbsent("api_materia_" + dto.getId(), dto);
            return dto;
        }).toList());

   //    response.setTotalPages(page.getTotalPages());


        return response;
    }


    @Override
    @Transactional
    public MateriaDTO update(Integer id, MateriaDTO dto) {
        if (dto.getEstado() != null && dto.getNombre() != null) {
            MateriaDTO mActualizada = materiaDao.findById(id).map(materiaDomain -> {
                materiaDomain.setNombre(dto.getNombre());
                materiaDomain.setEstado(dto.getEstado());
                dto.setId(materiaDomain.getId());
                return save(dto);
            }).orElse(null);

            if(mActualizada != null){
                cacheManager.getCache(Settings.CACHE_NAME).put("API_MATERIA_" + mActualizada.getId(), mActualizada);
            }
            return mActualizada;
        }
        return null;
    }



    @Override
    @Transactional
    public ResponseEntity<Boolean> delete(Integer id) {
        Boolean response = materiaDao.findById(id).map(materiaDomain -> {
            MateriaDTO dto = convertDomainToDto(materiaDomain);
            if (dto.getEstado()) {
                dto.setEstado(false);
                save(dto);
                return true;
            } else {
                return false;
            }
        }).orElse(null);
        return new ResponseEntity<Boolean>(response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }












}