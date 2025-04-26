package com.iron.springDemo.controllers;

import com.iron.springDemo.models.Employee;
import com.iron.springDemo.models.Patient;
import com.iron.springDemo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable int id){
        return patientRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
    }


}
