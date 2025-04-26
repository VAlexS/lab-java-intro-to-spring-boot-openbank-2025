package com.iron.springDemo.controllers;

import com.iron.springDemo.models.Patient;
import com.iron.springDemo.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getAllPatients(
            @RequestParam(required = false) Date startDate,
            @RequestParam(required = false) Date endDate,
            @RequestParam(required = false) String department) {

        List<Patient> result;

        if (startDate != null || endDate != null) {
            // Filtrar por rango de fecha de nacimiento
            result = patientRepository.findPatientsByDateOfBirthRange(
                    startDate != null ? startDate : null,
                    endDate != null ? endDate : null
            );
        } else if (department != null) {
            // Filtrar por el departamento del médico
            result = patientRepository.findPatientsByDoctorDepartment(department);
        } else {
            // Obtener todos los pacientes
            result = patientRepository.findAll();
        }

        if (!result.isEmpty()) {
            return result;
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron pacientes con los filtros proporcionados");
    }


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Patient getPatientById(@PathVariable int id){
        return patientRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Paciente no encontrado"));
    }

    @GetMapping("/by-doctor-status-off")
    @ResponseStatus(HttpStatus.OK)
    public List<Patient> getPatientsByDoctorWithStatusOff() {
        List<Patient> result = patientRepository.findPatientsByDoctorWithStatusOff();
        if (!result.isEmpty()) {
            return result;
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontraron pacientes con un médico en estado OFF");
    }




}
