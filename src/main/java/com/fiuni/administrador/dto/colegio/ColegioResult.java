package com.fiuni.administrador.dto.colegio;
import com.fiuni.administrador.dto.base.BaseResult;
import com.fiuni.administrador.dto.colegio.ColegioDto;

import java.util.List;

public class ColegioResult extends BaseResult<ColegioDto> {
    public ColegioResult() {

    }

    public ColegioResult(List<ColegioDto> list) {
        this.setList(list);
    }

    public List<ColegioDto> getLista() {
        return this.getList();
    }
}
