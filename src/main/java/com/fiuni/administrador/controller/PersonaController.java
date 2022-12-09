package com.fiuni.administrador.controller;
//DahyanaBarboza

import com.fiuni.administrador.dto.persona.PersonaDto;
import com.fiuni.administrador.dto.persona.PersonaResult;
import com.fiuni.administrador.service.persona.IPersonaService;
import com.fiuni.administrador.utils.Settings;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.apache.logging.log4j.LogManager.getLogger;

@RestController
@RequestMapping(path ="/personas")
public class PersonaController {
    public static final Logger l = getLogger(PersonaController.class);

    @Autowired

    private IPersonaService personaService;

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> getById(@PathVariable(value = "id") Integer id)throws Exception {
        PersonaDto dto = personaService.getById(id);

        return dto != null ? new ResponseEntity(dto, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping(path = "/page/{page_num}")
    public ResponseEntity<PersonaResult> getAll(@PathVariable(value = "page_num")Integer pageNum)throws Exception{
        PersonaResult response = personaService.getAll(PageRequest.of(pageNum, Settings.PAGE_SIZE));
        return response != null ? new ResponseEntity<PersonaResult>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    /*
    *
     @GetMapping(path = "/page/{page_num}")
    public ResponseEntity<MateriaResult> getAll(@PathVariable(value = "page_num")Integer pageNum){
        MateriaResult response = materiaService.getAll(PageRequest.of(pageNum, Settings.PAGE_SIZE));
        return response != null ? new ResponseEntity<MateriaResult>(response, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    *
    * */



    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping
    public ResponseEntity<PersonaDto> save(@Validated @RequestBody PersonaDto persona) throws Exception {
        PersonaDto response = personaService.save(persona);

        return response != null ? new ResponseEntity<PersonaDto>(response, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.CONFLICT);

        /*
        *@CrossOrigin(origins = "*")

	}
*/
    }


    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> putRol(@PathVariable(value = "id") Integer id, @RequestBody PersonaDto dto) {
        PersonaDto response = personaService.update(id, dto);

        return response != null ? new ResponseEntity<PersonaDto>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);

    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Boolean> deleteRol(@PathVariable(value = "id") Integer id) {
        Boolean response = personaService.delete(id);

        return new ResponseEntity<Boolean>(response != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }




}
