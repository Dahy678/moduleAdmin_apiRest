package com.fiuni.administrador.service.rol;

import com.fiuni.administrador.dto.rol.RolDto;
import com.fiuni.administrador.dto.rol.RolResult;
import com.fiuni.administrador.service.base.IBaseService;

public interface IRolService extends IBaseService <RolDto, RolResult> {

    public abstract RolDto update(Integer id, RolDto dto);

    public abstract Boolean delete(Integer id);
}

