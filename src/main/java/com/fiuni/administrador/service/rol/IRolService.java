package com.fiuni.administrador.service.rol;

import com.fiuni.administrador.dto.rol.RolDto;
import com.fiuni.administrador.dto.rol.RolResult;
import com.fiuni.administrador.service.base.IBaseService;
import org.springframework.http.ResponseEntity;

public interface IRolService extends IBaseService <RolDto, RolResult> {

    public abstract ResponseEntity<RolDto> update(Integer id, RolDto dto);

    public abstract ResponseEntity<Boolean> delete(Integer id);

    public abstract ResponseEntity<Integer> deleteAbs(Integer id);
}

