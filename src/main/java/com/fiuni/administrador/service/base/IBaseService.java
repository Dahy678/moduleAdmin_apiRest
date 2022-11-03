package com.fiuni.administrador.service.base;

import com.fiuni.administrador.dto.base.BaseDto;
import com.fiuni.administrador.dto.base.BaseResult;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface IBaseService<DTO extends BaseDto, R extends BaseResult<DTO>> {
    public ResponseEntity<DTO> save(DTO dto);

    public ResponseEntity<DTO> getById(Integer id);

    public ResponseEntity<R> getAll(Pageable pageable);

}
