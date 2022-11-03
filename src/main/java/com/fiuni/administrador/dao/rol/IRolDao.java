package com.fiuni.administrador.dao.rol;

import com.library.domainLibrary.domain.rol.RolDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface IRolDao extends CrudRepository<RolDomain, Integer> {
    public Page<RolDomain> findAll(Pageable pageable);


    @Modifying
    @Transactional
    @Query(value = "DELETE from RolDomain ed WHERE ((SELECT count(ev.idRol) FROM PersonaDomain  ev WHERE ev.idRol= ?1) <1 ) and ed.id= ?1 ")
    public Integer deleteAbsolut(Integer id);

}
