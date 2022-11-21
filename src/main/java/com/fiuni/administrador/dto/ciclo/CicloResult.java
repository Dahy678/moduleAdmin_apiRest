package com.fiuni.administrador.dto.ciclo;


import com.fiuni.administrador.dto.base.BaseResult;

import java.util.List;

public class CicloResult extends BaseResult<CicloDto> {
    public CicloResult() {

    }

    public CicloResult(List<CicloDto> list) {
        this.setList(list);
    }

    public List<CicloDto> getLista() {
        return this.getList();
    }
}
