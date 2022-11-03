package com.fiuni.administrador.dto.persona;

import com.fiuni.administrador.dto.base.BaseResult;

import java.util.List;

public class PersonaResult extends BaseResult<PersonaDto> {
    public PersonaResult() {

    }

    public PersonaResult(List<PersonaDto> list) {
        this.setList(list);
    }

    public List<PersonaDto> getLista() {
        return this.getList();
    }
}
