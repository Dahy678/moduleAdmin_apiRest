package com.fiuni.administrador.controller;


import com.fiuni.administrador.dto.materias.MateriaDTO;
import com.fiuni.administrador.dto.materias.MateriaResult;
import com.fiuni.administrador.dto.persona.PersonaResult;
import com.fiuni.administrador.dto.rol.RolResult;
import com.fiuni.administrador.service.materia.IMMaterialService;
import com.fiuni.administrador.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
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
        MateriaDTO dto = materiaService.getById(materiaId);
        return dto != null ? new ResponseEntity(dto, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping(path = "/page/{page_num}")
    public MateriaResult getAll(@PathVariable(value = "page_num")Integer pageNum)throws Exception{
        return materiaService.getAll(PageRequest.of(pageNum, Settings.PAGE_SIZE));

    }




    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping
    public ResponseEntity<MateriaDTO> save(@Validated @RequestBody MateriaDTO materia) throws Exception {
        MateriaDTO response = materiaService.save(materia);

        return response != null ? new ResponseEntity<MateriaDTO>(response, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @CrossOrigin(origins = "*", methods= {RequestMethod.PUT})
    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> putRol(@PathVariable(value = "id") Integer id, @RequestBody MateriaDTO dto) {

        MateriaDTO response = materiaService.update(id, dto);

        return response != null ? new ResponseEntity<MateriaDTO>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.DELETE})
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Boolean> deleteRol(@PathVariable(value = "id") Integer id) {
        return materiaService.delete(id);
    }





}
