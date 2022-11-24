package com.fiuni.administrador.controller;
//DahyanaBarboza

import com.fiuni.administrador.dto.persona.PersonaDto;
import com.fiuni.administrador.dto.persona.PersonaResult;
import com.fiuni.administrador.service.persona.IPersonaService;
import com.fiuni.administrador.utils.Settings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path ="/personas")
public class PersonaController {

    //private final Logger logger = LoggerFactory.getLogger(PersonaController.class);

    @Autowired
    private IPersonaService personaService;

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping("/{id}")
    public ResponseEntity<PersonaDto> getById(@PathVariable(value = "id") Integer cityId) {
        return personaService.getById(cityId);
    }

   // @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @GetMapping(path = "/page/{page_num}")
    public ResponseEntity<PersonaResult> getAll(@PathVariable(value = "page_num")Integer pageNum){
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
    public ResponseEntity<PersonaDto> save(@Validated @RequestBody PersonaDto persona) {
        return personaService.save(persona);
    }


    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> putRol(@PathVariable(value = "id") Integer id, @RequestBody PersonaDto dto) {
        return personaService.update(id, dto);
    }

    @CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST})
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Boolean> deleteRol(@PathVariable(value = "id") Integer id) {
        return personaService.delete(id);
    }

   /* @PostMapping("/users/login-user")
    @ResponseBody
    public ResponseEntity<PersonaDto> loginUser(@Validated @RequestBody PersonaDto dto){

        PersonaDto userEmailExists = personaDao.findUserByEmail(email).map(personaDomain -> convertDomainToDto(personaDomain)).orElse(null);

        User userEmailExists = userService.findUserByEmail(user.getEmail());
        User userMobileExists = userService.findUserByMobile((user.getMobile()));

        String existingPassword =userEmailExists.getPassword();
        String currentPassword=user.getPassword();

        if (userEmailExists.getEmail().isEmpty()) {
            return new ApiResponse<>("\"Oops.! User email not found, please register.\"", com.bfarming.response.ResponseStatus.getValidResponseStatus(HttpStatus.OK));
        }else if(userMobileExists.getMobile().isEmpty()){
            return new ApiResponse<>("\"Oops.! User mobile not found, please register.\"", com.bfarming.response.ResponseStatus.getValidResponseStatus(HttpStatus.OK));
        }else if (bcryptGenerator.passwordDecoder(currentPassword,existingPassword)) {
            return new ApiResponse<>("\"Password Exists, logged-in\"");
        }else {
            return new ApiResponse<>("\"Password didn't match, please enter the correct password, logged-in\"");
        }
    }
*/




}
