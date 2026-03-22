package dev.markrz0.assetmanagement.service;

import dev.markrz0.assetmanagement.model.dto.EmployeeCreateRequest;
import dev.markrz0.assetmanagement.model.dto.EmployeeResponse;
import dev.markrz0.assetmanagement.model.entity.Employee;
import dev.markrz0.assetmanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public EmployeeResponse createEmployee(EmployeeCreateRequest request) {
        Employee newEmployee = Employee.builder()
                .name(request.name())
                .department(request.department())
                .build();

        Employee savedEmployee = employeeRepository.save(newEmployee);
        return mapToResponse(savedEmployee);
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getDepartment()
        );
    }
}