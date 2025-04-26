package com.iron.springDemo.controllers;

import com.iron.springDemo.models.Employee;
import com.iron.springDemo.models.Status;
import com.iron.springDemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;


    /*Los requestParam con required false quiere decir que son opcionales, con esto me aseguro que el método asociado permita endpoints dinámicos.
    Además, de hacerlos en métodos a parte, tendría que rellenar los GetMapping, ya que JPA, si encuentra más de un getMaping vacío, se produciría
    errores y no se podría levantar el servidor al detectar ambiguedad. Y la idea es que al meter parametros en un endpoint, este no sea
    modificado*/
    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees(@RequestParam(required = false) Status status,
                                          @RequestParam(required = false) String department) {
        List<Employee> result = employeeRepository.findEmployeesByFilters(
                status != null ? status : null,
                department != null ? department : null
        );

        if (!result.isEmpty()) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron empleados con los filtros proporcionados");
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee getEmployeeById(@PathVariable int id){
        return employeeRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Empleado no encontrado"));
    }





}
