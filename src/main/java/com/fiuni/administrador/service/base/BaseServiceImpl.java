package com.fiuni.administrador.service.base;

import com.fiuni.administrador.dto.base.BaseDto;

import com.fiuni.administrador.dto.base.BaseResult;

import com.library.domainLibrary.domain.base.BaseDomain;

public abstract class BaseServiceImpl<DTO extends BaseDto, DOMAIN extends BaseDomain,  RESULT extends BaseResult<DTO>> implements IBaseService<DTO, RESULT> {

    protected abstract DTO convertDomainToDto(DOMAIN domain);

    protected abstract DOMAIN convertDtoToDomain(DTO dto);

}