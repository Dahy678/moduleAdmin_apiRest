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

    @GetMapping("/{id}")
    public ResponseEntity<RolDto> getById(@PathVariable(value = "id") Integer cityId) {
        return rolService.getById(cityId);
    }

    @GetMapping(path = "/page/{page_num}")
    public ResponseEntity<RolResult> getClients(@PathVariable(value = "page_num")Integer pageNum) {
        return rolService.getAll(PageRequest.of(pageNum, Settings.PAGE_SIZE));
    }
    @PostMapping
    public ResponseEntity<RolDto> save(@Validated @RequestBody RolDto rol) {
        return rolService.save(rol);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDto> putRol(@PathVariable(value = "id") Integer id, @RequestBody RolDto dto) {
        return rolService.update(id, dto);
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Boolean> deleteRol(@PathVariable(value = "id") Integer id) {
        return rolService.delete(id);
    }

    @DeleteMapping("/absolute/{id}")
    public ResponseEntity<Integer> deleteAbsRol(@PathVariable(value = "id") Integer id){
        return rolService.deleteAbs(id);
    }





}
