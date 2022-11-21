package com.fiuni.administrador.dto.rol;

import java.util.List;

import com.fiuni.administrador.dto.base.BaseResult;

public class RolResult extends BaseResult<RolDto> {
    public RolResult() {

    }

    public RolResult(List<RolDto> list) {
        this.setList(list);
    }

    public List<RolDto> getLista() {
        return this.getList();
    }
}
