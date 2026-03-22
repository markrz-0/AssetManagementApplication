package dev.markrz0.assetmanagement.model.dto;

import java.util.List;

public record EmployeeHistoryResponse(
        Long employeeId,
        String employeeName,
        String department,
        List<CheckoutResponse> activeAndPastCheckouts
) {}