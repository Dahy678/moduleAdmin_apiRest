package com.fiuni.administrador.dto.contactoEmergencia;




import com.fiuni.administrador.dto.base.BaseResult;

import java.util.List;


public class ContactoEmergenciasResult extends BaseResult<ContactoEmergenciasDto> {
    public ContactoEmergenciasResult() {

    }

    public ContactoEmergenciasResult(List<ContactoEmergenciasDto> list) {
        this.setList(list);
    }

    public List<ContactoEmergenciasDto> getLista() {
        return this.getList();
    }
}
