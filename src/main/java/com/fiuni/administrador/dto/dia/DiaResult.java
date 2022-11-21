package com.fiuni.administrador.dto.dia;



import com.fiuni.administrador.dto.base.BaseResult;

import java.util.List;

public class DiaResult extends BaseResult<DiaDto> {
    public DiaResult() {

    }

    public DiaResult(List<DiaDto> list) {
        this.setList(list);
    }

    public List<DiaDto> getLista() {
        return this.getList();
    }
}

