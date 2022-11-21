package com.fiuni.administrador.service.materia;

import com.fiuni.administrador.dao.materia.IMateriaDao;
import com.fiuni.administrador.dto.materias.MateriaDTO;
import com.fiuni.administrador.dto.materias.MateriaResult;
import com.fiuni.administrador.service.base.BaseServiceImpl;
import com.fiuni.administrador.utils.Settings;
import com.library.domainLibrary.domain.materia.MateriaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class MMateriaServiceImple extends BaseServiceImpl<MateriaDTO, MateriaDomain, MateriaResult> implements IMMaterialService {



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
    public ResponseEntity<MateriaDTO> save(MateriaDTO dto) {
        dto.setEstado(dto.getEstado() == null ? true : dto.getEstado());
        MateriaDTO response = convertDomainToDto(materiaDao.save(convertDtoToDomain(dto)));


        return response != null ? new ResponseEntity<MateriaDTO>(response, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Override
    @Transactional
    public ResponseEntity<MateriaDTO> getById(Integer id) {
        Optional<MateriaDomain> materiaDomain = materiaDao.findById(id);
        MateriaDTO response = materiaDomain.map(m -> {
            return convertDomainToDto(m);
        }).orElse(null);

        return response != null ? new ResponseEntity(response, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @Override
    @Transactional
    public ResponseEntity<MateriaResult> getAll(Pageable pageable) {
        MateriaResult result = new MateriaResult(materiaDao.findAll(pageable).map(rol -> {
            MateriaDTO dto= convertDomainToDto(rol);

            cacheManager.getCache(Settings.CACHE_NAME).putIfAbsent("api_materia_" + dto.getId(), dto);
            return dto;

        }).toList());

        return result != null ? new ResponseEntity<MateriaResult>(result, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Override
    @Transactional
    public ResponseEntity<MateriaDTO> update(Integer id, MateriaDTO dto) {
        if (dto.getEstado() != null && dto.getNombre() != null) {
            MateriaDTO mActualizada = materiaDao.findById(id).map(materiaDomain -> {
                materiaDomain.setNombre(dto.getNombre());
                materiaDomain.setEstado(dto.getEstado());
                dto.setId(materiaDomain.getId());
                return save(dto);
            }).orElse(null).getBody();
            return (mActualizada != null ? new ResponseEntity(HttpStatus.NO_CONTENT) : new ResponseEntity(HttpStatus.CONFLICT));

        }
        return new ResponseEntity<MateriaDTO>(HttpStatus.BAD_REQUEST);
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
