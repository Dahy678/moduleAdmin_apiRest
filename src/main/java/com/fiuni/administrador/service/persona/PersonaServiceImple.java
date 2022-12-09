package com.fiuni.administrador.service.persona;

import com.fiuni.administrador.dao.persona.IPersonaDao;
import com.fiuni.administrador.dao.rol.IRolDao;
import com.fiuni.administrador.dto.persona.PersonaDto;
import com.fiuni.administrador.dto.persona.PersonaResult;
import com.fiuni.administrador.dto.rol.RolDto;
import com.fiuni.administrador.service.base.BaseServiceImpl;
import com.fiuni.administrador.utils.Settings;
import com.library.domainLibrary.domain.persona.PersonaDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public  class PersonaServiceImple extends BaseServiceImpl<PersonaDto ,PersonaDomain ,PersonaResult> implements IPersonaService {



    @Autowired
    private IPersonaDao personaDao;

    @Autowired
    private IRolDao rolDao;

    @Autowired
    private CacheManager cacheManager;


    @Override
    @Transactional
    public PersonaDto update(Integer id, PersonaDto dto) {
        if (dto.getEstado() != null && dto.getIdRol() != null && dto.getNombre() != null && dto.getCi() != null
                && dto.getDireccion() !=null && dto.getEmail()!= null && dto.getFechaNacimiento()!=null && dto.getGenero() != null && dto.getPassword()!= null
                && dto.getTelefono() !=null ) {
            PersonaDto response = personaDao.findById(id).map(personaDomain -> {
                personaDomain.setNombre(dto.getNombre());
                personaDomain.setEstado(dto.getEstado());
                personaDomain.setIdRol(dto.getIdRol());
                personaDomain.setId(dto.getId());
                personaDomain.setDireccion(dto.getDireccion());
                personaDomain.setEmail(dto.getEmail());
                personaDomain.setFechaNacimiento(dto.getFechaNacimiento());
                personaDomain.setGenero(dto.getGenero());
                personaDomain.setCi(dto.getCi());
                personaDomain.setPassword(dto.getPassword());
                dto.setId(personaDomain.getId());
                cacheManager.getCache(Settings.CACHE_NAME).evictIfPresent("API_PERSONA_" + id);
                return save(dto);
            }).orElse(null);
            if(response != null){
                cacheManager.getCache(Settings.CACHE_NAME).put("API_PERSONA_" + response.getId(), response);
            }
            return response;
        }
        return null;

    }

    @Override
    protected PersonaDto convertDomainToDto(PersonaDomain domain) {

        PersonaDto dto =new PersonaDto();
        dto.setNombre(domain.getNombre());
        dto.setEstado(domain.getEstado());
        dto.setIdRol(domain.getIdRol());
        dto.setId(domain.getId());
        dto.setDireccion(domain.getDireccion());
        dto.setEmail(domain.getEmail());
        dto.setFechaNacimiento(domain.getFechaNacimiento());
        dto.setGenero(domain.getGenero());
        dto.setCi(domain.getCi());
        dto.setPassword(domain.getPassword());
        dto.setId(domain.getId());
        dto.setRolDto(rolDao.findById(domain.getIdRol()).map(rolDomain -> {
            RolDto roldto = new RolDto();

            roldto.setId(rolDomain.getId());
            roldto.setNombre(rolDomain.getNombre());
            roldto.setEstado(rolDomain.getEstado());
            return  roldto;


        }).orElse(null));
        dto.setIdRol(domain.getIdRol());

        return dto;

    }



    @Override
    @Transactional
    public PersonaDto save(PersonaDto dto) {
        dto.setEstado(dto.getEstado() == null ? true:  dto.getEstado());

        PersonaDto response = convertDomainToDto(personaDao.save(convertDtoToDomain(dto)));

        if(dto.getId() == null){
            cacheManager.getCache(Settings.CACHE_NAME).put("API_PERSONA_" + response.getId(), response);
        }

        return response;
    }

    @Override
    @Transactional
    @Cacheable(value =Settings.CACHE_NAME,key="'api_persona_'+ #id")
    public PersonaDto getById(Integer id) {
        Optional<PersonaDomain> personaDomain = personaDao.findById(id);
        PersonaDto response = personaDomain.map(persona -> {
            return convertDomainToDto(persona);
        }).orElse(null);

        return response;

    }

    @Override
    @Transactional
    public PersonaResult getAll(Pageable pageable) {
        Page<PersonaDomain> page = personaDao.getByEstadoTrue(pageable);
        PersonaResult response = new PersonaResult(page.map(materia -> {
            PersonaDto dto = convertDomainToDto(materia);
            cacheManager.getCache(Settings.CACHE_NAME).putIfAbsent("api_persona_" + dto.getId(), dto);
            return dto;
        }).toList());

        return response ;
    }


    @Override
    protected PersonaDomain convertDtoToDomain(PersonaDto dto) {

        PersonaDomain personaDomain = new PersonaDomain();
        personaDomain.setNombre(dto.getNombre());
        personaDomain.setEstado(dto.getEstado());
        personaDomain.setIdRol(dto.getIdRol());
        personaDomain.setId(dto.getId());
        personaDomain.setDireccion(dto.getDireccion());
        personaDomain.setEmail(dto.getEmail());
        personaDomain.setFechaNacimiento(dto.getFechaNacimiento());
        personaDomain.setGenero(dto.getGenero());
        personaDomain.setCi(dto.getCi());
        personaDomain.setPassword(dto.getPassword());
        dto.setId(personaDomain.getId());

        return personaDomain;
    }

    @Override
    @Transactional
    public Boolean delete(Integer id) {
        Boolean response = personaDao.findById(id).map(personaDomain -> {
            PersonaDto dto = convertDomainToDto(personaDomain);
            if (dto.getEstado()) {
                dto.setEstado(false);
                save(dto);

                PersonaDto respuesta = save(dto);
                if(respuesta != null){
                    cacheManager.getCache(Settings.CACHE_NAME).evictIfPresent("API_PERSONA_" + id);
                }

                return true;
            } else {
                return false;
            }
        }).orElse(false);

        return response;


    }











}






