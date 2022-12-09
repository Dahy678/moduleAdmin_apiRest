package com.fiuni.administrador.service.base;

import com.fiuni.administrador.dto.base.BaseDto;
import com.fiuni.administrador.dto.base.BaseResult;
import org.springframework.data.domain.Pageable;


public interface IBaseService<DTO extends BaseDto, R extends BaseResult<DTO>> {

    public DTO save(DTO dto) throws Exception;

    public DTO getById(Integer id);

     //public ResponseEntity<DTO> login(DTO dto);


    public R getAll(Pageable pageable);

/*
*  public DTO save(DTO dto) throws Exception;

    public DTO getById(Integer id) throws Exception;



    public R getAll(Pageable pageable) throws Exception;*/

}
