package com.fiuni.administrador.service.materia;

import com.fiuni.administrador.dto.materias.MateriaDTO;
import com.fiuni.administrador.dto.materias.MateriaResult;
import com.fiuni.administrador.service.base.IBaseService;
import org.springframework.http.ResponseEntity;

public interface IMMaterialService extends IBaseService<MateriaDTO, MateriaResult> {

    public abstract ResponseEntity<MateriaDTO> update(Integer id, MateriaDTO dto);

    public abstract ResponseEntity<Boolean> delete(Integer id);




}