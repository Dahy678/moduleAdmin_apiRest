package com.fiuni.administrador.service.colegio;


import com.fiuni.administrador.dto.colegio.ColegioDto;
import com.fiuni.administrador.dto.colegio.ColegioResult;
import com.fiuni.administrador.service.base.IBaseService;

public interface IColegioService extends IBaseService<ColegioDto, ColegioResult> {

    public abstract ColegioDto update(Integer id, ColegioDto dto);

    public abstract Boolean delete(Integer id);
}
