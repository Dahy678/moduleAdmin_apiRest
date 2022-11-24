package com.fiuni.administrador.controller;

import com.fiuni.administrador.dto.rol.RolDto;
import com.fiuni.administrador.dto.rol.RolResult;
import com.fiuni.administrador.service.rol.IRolService;
import com.fiuni.administrador.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
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
    public ResponseEntity<RolDto> getById(@PathVariable(value = "id") Integer cityId) {
        return rolService.getById(cityId);
    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping(path = "/page/{page_num}")
    public RolResult getClients(@PathVariable(value = "page_num")Integer pageNum) {
        return rolService.getAll(PageRequest.of(pageNum, Settings.PAGE_SIZE));
    }
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PostMapping
    public ResponseEntity<RolDto> save(@Validated @RequestBody RolDto rol) {
        return rolService.save(rol);
    }
    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PutMapping("/{id}")
    public ResponseEntity<RolDto> putRol(@PathVariable(value = "id") Integer id, @RequestBody RolDto dto) {
        return rolService.update(id, dto);
    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Boolean> deleteRol(@PathVariable(value = "id") Integer id) {
        return rolService.delete(id);
    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @DeleteMapping("/absolute/{id}")
    public ResponseEntity<Integer> deleteAbsRol(@PathVariable(value = "id") Integer id){
        return rolService.deleteAbs(id);
    }


}
