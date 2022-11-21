package com.fiuni.administrador.dao.persona;

import com.fiuni.administrador.dto.persona.PersonaDto;
import com.library.domainLibrary.domain.persona.PersonaDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IPersonaDao extends CrudRepository<PersonaDomain, Integer> {
    public Page<PersonaDomain> findAll(Pageable pageable);




   // public ResponseEntity<PersonaDto> getByLogin(String email, String password);

}

