package com.fiuni.administrador.service.contactoEmergencia;

import com.fiuni.administrador.dto.contactoEmergencia.ContactoEmergenciasDto;
import com.fiuni.administrador.dto.contactoEmergencia.ContactoEmergenciasResult;
import com.fiuni.administrador.service.base.IBaseService;



public interface IContactoEmergenciasService extends IBaseService<ContactoEmergenciasDto, ContactoEmergenciasResult> {

    public abstract ContactoEmergenciasDto update(Integer id, ContactoEmergenciasDto dto);

    public abstract Boolean delete(Integer id);
}

