package com.fiuni.administrador.dao.horaCatedra;


import com.library.domainLibrary.domain.horaCatedra.HoraCatedraDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IHoraCatedraDao extends CrudRepository<HoraCatedraDomain, Integer> {
    public Page<HoraCatedraDomain> findAll(Pageable pageable);

}

