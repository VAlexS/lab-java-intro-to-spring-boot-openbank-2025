package com.iron.springDemo.repositories;

import com.iron.springDemo.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

/*como se puede observar, las interfaces si que permiten herencia multiple. La segunda interfaz de la que hereda esta es para que un endpoint
 * que admita parámetros esté asignado a un unico metodo*/
public interface PatientRepository extends JpaRepository<Patient, Integer>, JpaSpecificationExecutor<Patient> {


    @Query("SELECT p FROM Patient p WHERE "
            + "(:startDate IS NULL OR p.dateOfBirth >= :startDate) "
            + "AND (:endDate IS NULL OR p.dateOfBirth <= :endDate)")
    List<Patient> findPatientsByDateOfBirthRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


    @Query("SELECT p FROM Patient p WHERE "
            + "(:department IS NULL OR p.employee.department LIKE %:department%)")
    List<Patient> findPatientsByDoctorDepartment(@Param("department") String department);


    @Query("SELECT p FROM Patient p WHERE "
            + "p.employee.status = 'OFF'")
    List<Patient> findPatientsByDoctorWithStatusOff();
}
