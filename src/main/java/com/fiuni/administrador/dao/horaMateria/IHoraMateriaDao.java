package com.fiuni.administrador.dao.horaMateria;

import com.library.domainLibrary.domain.materia.MateriaDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface IHoraMateriaDao extends CrudRepository<MateriaDomain, Integer> {
    public Page<IHoraMateriaDao> findAll(Pageable pageable);


}