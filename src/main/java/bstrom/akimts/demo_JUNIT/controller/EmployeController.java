package bstrom.akimts.demo_JUNIT.controller;

import bstrom.akimts.demo_JUNIT.entity.Employe;
import bstrom.akimts.demo_JUNIT.exception.NotFoundException;
import bstrom.akimts.demo_JUNIT.model.EmployeDTO;
import bstrom.akimts.demo_JUNIT.service.EmployeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employe")
public class EmployeController {

    private final EmployeService service;

    public EmployeController(EmployeService service) {
        this.service = service;
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public EmployeDTO getOne(@PathVariable Long id) {
        return service.getOne(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeDTO insert(@RequestBody EmployeDTO dto) {
        return service.insert(dto);
    }

    @DeleteMapping("/{id}")
    public EmployeDTO delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @ExceptionHandler( NotFoundException.class )
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handleNotFound() { }

    @ExceptionHandler( IllegalArgumentException.class )
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void handleIllegal() { }


}
