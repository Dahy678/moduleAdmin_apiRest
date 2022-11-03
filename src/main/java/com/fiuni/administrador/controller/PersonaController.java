package com.fiuni.administrador.controller;


import com.fiuni.administrador.dto.persona.PersonaDto;
import com.fiuni.administrador.dto.rol.RolDto;
import com.fiuni.administrador.service.persona.IPersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path ="/personas")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> getById(@PathVariable(value = "id") Integer cityId) {
        return personaService.getById(cityId);
    }


}
