package com.fiuni.administrador.dao.colegios;

import com.library.domainLibrary.domain.colegio.ColegioDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IColegioDao extends CrudRepository<ColegioDomain, Integer> {
    public Page<ColegioDomain> findAll(Pageable pageable);

}