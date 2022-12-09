package com.fiuni.administrador.service.persona;

import com.fiuni.administrador.dto.persona.PersonaDto;
import com.fiuni.administrador.dto.persona.PersonaResult;
import com.fiuni.administrador.service.base.IBaseService;

public interface IPersonaService extends IBaseService<PersonaDto, PersonaResult> {

    public abstract PersonaDto update(Integer id, PersonaDto dto);

    public abstract Boolean delete(Integer id);

    //ResponseEntity<PersonaDto> login(PersonaDto persona);

 //   @Transactional


   // public DTO getById(Integer id) throws Exception;
   /*
   *
   *   public abstract EtapaDTO update(Integer id, EtapaDTO dto);

    public abstract Boolean delete(Integer id);

    public abstract Integer deleteAbs(Integer id);


    public abstract Boolean saveAll(EtapaListEvalDTO dto);
   * */



}
