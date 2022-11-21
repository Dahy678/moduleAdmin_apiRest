package com.fiuni.administrador.service.persona;

import com.fiuni.administrador.dto.persona.PersonaDto;
import com.fiuni.administrador.dto.persona.PersonaResult;
import com.fiuni.administrador.service.base.IBaseService;
import org.springframework.http.ResponseEntity;

public interface IPersonaService extends IBaseService<PersonaDto, PersonaResult> {

    public abstract ResponseEntity<PersonaDto> update(Integer id, PersonaDto dto);

    public abstract ResponseEntity<Boolean> delete(Integer id);

    //ResponseEntity<PersonaDto> login(PersonaDto persona);

 //   @Transactional


    public ResponseEntity<PersonaDto> getById(Integer id);
    // ResponseEntity<PersonaDto> login(PersonaDto persona);
}
