package com.iron.springDemo.controllers;

import com.iron.springDemo.auxiliares.EmployeeSpecification;
import com.iron.springDemo.models.Employee;
import com.iron.springDemo.models.Status;
import com.iron.springDemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;



    /*@GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllEmployees(@RequestParam(required = false) Status status){

        //valido si hay o no hay parámetros, en caso de recibir parámetros, que es el status, y el usuario le mande un status no válido,
        // JPA lo detecta automáticamente y devuelve BAD_REQUEST
        if (status != null) {

            List<Employee> result = employeeRepository.findEmployeesByStatus(status);
            if (!result.isEmpty())
                return result;
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron empleados con el estado: " + status);

        }
        return employeeRepository.findAll();
    }*/

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
