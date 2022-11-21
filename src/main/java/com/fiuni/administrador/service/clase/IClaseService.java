package com.fiuni.administrador.service.clase;

import com.fiuni.administrador.dto.clase.ClaseDTO;
import com.fiuni.administrador.dto.clase.ClaseResult;
import com.fiuni.administrador.service.base.IBaseService;

public interface IClaseService extends IBaseService<ClaseDTO, ClaseResult> {

    public abstract ClaseDTO update(Integer id, ClaseDTO dto);

    public abstract Boolean delete(Integer id);
}