package com.fiuni.administrador.controller;


import com.fiuni.administrador.dto.persona.PersonaDto;
import com.fiuni.administrador.dto.persona.PersonaResult;
import com.fiuni.administrador.service.persona.IPersonaService;
import com.fiuni.administrador.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/personas")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> getById(@PathVariable(value = "id") Integer cityId) {
        return personaService.getById(cityId);
    }

    @GetMapping(path = "/page/{page_num}")

    public ResponseEntity<PersonaResult> getClients(@PathVariable(value = "page_num")Integer pageNum){
        return personaService.getAll(PageRequest.of(pageNum, Settings.PAGE_SIZE));

    }


    @PostMapping
    public ResponseEntity<PersonaDto> save(@Validated @RequestBody PersonaDto persona) {
        return personaService.save(persona);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> putRol(@PathVariable(value = "id") Integer id, @RequestBody PersonaDto dto) {
        return personaService.update(id, dto);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Boolean> deleteRol(@PathVariable(value = "id") Integer id) {
        return personaService.delete(id);
    }



}
