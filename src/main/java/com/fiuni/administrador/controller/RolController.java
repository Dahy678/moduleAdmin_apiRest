package com.fiuni.administrador.controller;

import com.fiuni.administrador.dto.rol.RolDto;
import com.fiuni.administrador.dto.rol.RolResult;
import com.fiuni.administrador.service.rol.IRolService;
import com.fiuni.administrador.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController

//@CrossOrigin(origins= "*")

@RequestMapping(path = "/roles")
public class RolController {

    @Autowired
    private IRolService rolService;

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/{id}")
    public ResponseEntity<RolDto> getById(@PathVariable(value = "id") Integer id)throws Exception {
        RolDto dto = rolService.getById(id);

        return dto != null ? new ResponseEntity(dto, HttpStatus.OK)
                : new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping(path = "/page/{page_num}")
    public RolResult getAll(@PathVariable(value = "page_num")Integer pageNum) {
        return rolService.getAll(PageRequest.of(pageNum, Settings.PAGE_SIZE));
    }
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping
    public ResponseEntity<RolDto> save(@Validated @RequestBody RolDto rol) throws Exception {
        RolDto response = rolService.save(rol);

        return response != null ? new ResponseEntity<RolDto>(response, HttpStatus.CREATED)
                : new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT})
    @PutMapping("/{id}")
    public ResponseEntity<RolDto> putRol(@PathVariable(value = "id") Integer id, @RequestBody RolDto dto) {
        RolDto response = rolService.update(id, dto);

        return response != null ? new ResponseEntity<RolDto>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CONFLICT);
    }


    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.PUT})

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Boolean> deleteRol(@PathVariable(value = "id") Integer id) {
        return rolService.delete(id);
    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    @DeleteMapping("/absolute/{id}")
    public ResponseEntity<Integer> deleteAbsRol(@PathVariable(value = "id") Integer id){
        Integer response = rolService.deleteAbs(id);

        return new ResponseEntity<Integer>(response, response > 0 ? HttpStatus.OK : HttpStatus.METHOD_NOT_ALLOWED);
    }


}
