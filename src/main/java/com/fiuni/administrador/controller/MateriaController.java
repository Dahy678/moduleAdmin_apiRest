package com.fiuni.administrador.controller;


import com.fiuni.administrador.dto.materias.MateriaDTO;
import com.fiuni.administrador.dto.materias.MateriaResult;
import com.fiuni.administrador.dto.persona.PersonaDto;
import com.fiuni.administrador.dto.persona.PersonaResult;
import com.fiuni.administrador.service.materia.IMMaterialService;
import com.fiuni.administrador.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController

@RequestMapping(path = "/materias")
public class MateriaController {

    @Autowired
    private IMMaterialService materiaService;


    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> getById(@PathVariable(value = "id") Integer materiaId) {
        return materiaService.getById(materiaId);
    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping(path = "/page/{page_num}")
    public ResponseEntity<MateriaResult> getAll(@PathVariable(value = "page_num")Integer pageNum){
        return materiaService.getAll(PageRequest.of(pageNum, Settings.PAGE_SIZE));

    }


    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping
    public ResponseEntity<MateriaDTO> save(@Validated @RequestBody MateriaDTO materia) {
        return materiaService.save(materia);
    }


    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> putRol(@PathVariable(value = "id") Integer id, @RequestBody MateriaDTO dto) {
        return materiaService.update(id, dto);
    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Boolean> deleteRol(@PathVariable(value = "id") Integer id) {
        return materiaService.delete(id);
    }





}
