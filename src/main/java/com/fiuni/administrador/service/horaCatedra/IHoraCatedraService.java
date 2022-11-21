package com.fiuni.administrador.service.horaCatedra;

import com.fiuni.administrador.dto.horaCatedra.HoraCatedraDto;
import com.fiuni.administrador.dto.horaCatedra.HoraCatedraResult;
import com.fiuni.administrador.service.base.IBaseService;


public interface IHoraCatedraService extends IBaseService<HoraCatedraDto, HoraCatedraResult> {

    public abstract HoraCatedraDto update(Integer id, HoraCatedraDto dto);

    public abstract Boolean delete(Integer id);
}
