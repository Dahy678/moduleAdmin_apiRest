package com.fiuni.administrador.dto.horaCatedra;


import com.fiuni.administrador.dto.base.BaseResult;

import java.util.List;

public class HoraCatedraResult extends BaseResult<HoraCatedraDto> {
    public HoraCatedraResult() {

    }

    public HoraCatedraResult(List<HoraCatedraDto> list) {
        this.setList(list);
    }

    public List<HoraCatedraDto> getLista() {
        return this.getList();
    }
}
