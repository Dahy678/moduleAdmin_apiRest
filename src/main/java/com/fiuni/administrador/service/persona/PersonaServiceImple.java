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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
    public ResponseEntity<PersonaDto> update(Integer id, PersonaDto dto) {
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
                return save(dto);
            }).orElse(null).getBody();
            return response != null ? new ResponseEntity<PersonaDto>(HttpStatus.NO_CONTENT)
                    : new ResponseEntity<>(HttpStatus.CONFLICT);

        }
        return new ResponseEntity<PersonaDto>(HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<PersonaDto> save(PersonaDto dto) {
        dto.setEstado(dto.getEstado() == null || dto.getEstado());

        PersonaDto response = convertDomainToDto(personaDao.save(convertDtoToDomain(dto)));

        return response != null ? new ResponseEntity<PersonaDto>(response, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @Override
    @Transactional
   // @Cacheable(value =Settings.CACHE_NAME,key="'api_persona_'+ #id")
    public ResponseEntity<PersonaDto> getById(Integer id) {
        PersonaDto response = personaDao.findById(id).map(personaDomain -> convertDomainToDto(personaDomain)).orElse(null);

        return response != null ? new ResponseEntity<PersonaDto>(response, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);

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
    public ResponseEntity<Boolean> delete(Integer id) {
        Boolean response = personaDao.findById(id).map(personaDomain -> {
            PersonaDto dto = convertDomainToDto(personaDomain);
            if (dto.getEstado()) {
                dto.setEstado(false);
                save(dto);
                return true;
            } else {
                return false;
            }
        }).orElse(false);

        return new ResponseEntity<Boolean>(response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }











}






