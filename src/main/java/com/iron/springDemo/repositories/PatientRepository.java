package com.iron.springDemo.repositories;

import com.iron.springDemo.models.Employee;
import com.iron.springDemo.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PatientRepository extends JpaRepository<Patient, Integer>, JpaSpecificationExecutor<Patient> {
}
