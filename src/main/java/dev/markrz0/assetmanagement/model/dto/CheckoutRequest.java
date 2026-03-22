package dev.markrz0.assetmanagement.model.dto;
import jakarta.validation.constraints.NotNull;

public record CheckoutRequest(
        @NotNull(message = "Employee ID is required") Long employeeId,
        @NotNull(message = "Device ID is required") Long deviceId
) {}