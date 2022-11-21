package com.fiuni.administrador.service.ciclo;

import com.fiuni.administrador.dto.ciclo.CicloDto;
import com.fiuni.administrador.dto.ciclo.CicloResult;
import com.fiuni.administrador.service.base.IBaseService;


public interface ICicloService extends IBaseService<CicloDto, CicloResult> {

    public abstract CicloDto update(Integer id, CicloDto dto);

    public abstract Boolean delete(Integer id);
}
