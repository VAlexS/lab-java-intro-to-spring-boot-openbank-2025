package com.iron.springDemo.auxiliares;

import com.iron.springDemo.models.Patient;
import com.iron.springDemo.models.Status;
import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class PatientSpecification {

    public static Specification<Patient> withFilters(Map<String, String> params) {
        return (root, query, criteriaBuilder) -> {
            var predicates = criteriaBuilder.conjunction();

            params.forEach((key, value) -> {
                switch (key) {
                    case "status":
                        predicates.getExpressions().add(criteriaBuilder.equal(root.get("status"), Status.valueOf(value)));
                        break;
                    case "department":
                        predicates.getExpressions().add(criteriaBuilder.like(root.get("department"), "%" + value + "%"));
                        break;
                    default:
                        // Ignorar parámetros desconocidos
                        break;
                }
            });

            return predicates;
        };
    }
}
