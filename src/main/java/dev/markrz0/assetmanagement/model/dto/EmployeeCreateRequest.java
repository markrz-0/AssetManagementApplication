package dev.markrz0.assetmanagement.model.dto;

import jakarta.validation.constraints.NotBlank;

public record EmployeeCreateRequest(
        @NotBlank(message = "Employee name is required") String name,
        @NotBlank(message = "Department is required") String department
) {}