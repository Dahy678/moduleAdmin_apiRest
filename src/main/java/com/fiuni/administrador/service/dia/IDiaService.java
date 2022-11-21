package com.fiuni.administrador.service.dia;

import com.fiuni.administrador.dto.dia.DiaDto;
import com.fiuni.administrador.dto.dia.DiaResult;
import com.fiuni.administrador.service.base.IBaseService;

public interface IDiaService extends IBaseService<DiaDto, DiaResult> {

    public abstract DiaDto update(Integer id, DiaDto dto);

    public abstract Boolean delete(Integer id);
}

