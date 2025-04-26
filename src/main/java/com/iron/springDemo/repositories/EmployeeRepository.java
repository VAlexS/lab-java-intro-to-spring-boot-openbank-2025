package com.iron.springDemo.repositories;

import com.iron.springDemo.models.Employee;
import com.iron.springDemo.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/*como se puede observar, las interfaces si que permiten herencia multiple. La segunda interfaz de la que hereda esta es para que un endpoint
* que admita parámetros esté asignado a un unico metodo*/
public interface EmployeeRepository extends JpaRepository<Employee, Integer>, JpaSpecificationExecutor<Employee> {

    @Query("SELECT e FROM Employee e WHERE "
            + "(:status IS NULL OR e.status = :status) "
            + "AND (:department IS NULL OR e.department LIKE %:department%)")
    List<Employee> findEmployeesByFilters(@Param("status") Status status, @Param("department") String department);
}
