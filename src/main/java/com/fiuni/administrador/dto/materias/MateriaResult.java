package com.fiuni.administrador.dto.materias;

import com.fiuni.administrador.dto.base.BaseResult;

import java.util.List;

public class MateriaResult extends BaseResult<MateriaDTO> {
    public MateriaResult() {

    }

    public MateriaResult(List<MateriaDTO> list) {
        this.setList(list);
    }

    public List<MateriaDTO> getLista() {
        return this.getList();
    }
}

