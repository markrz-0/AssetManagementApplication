package dev.markrz0.assetmanagement.repository;

import dev.markrz0.assetmanagement.model.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query("""
           SELECT e FROM Employee e
           LEFT JOIN FETCH e.checkoutRecords cr
           LEFT JOIN FETCH cr.device
           WHERE e.id = :id
           """)
    Optional<Employee> findByIdWithHistory(@Param("id") Long id);
}