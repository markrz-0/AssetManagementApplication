package dev.markrz0.assetmanagement.model.dto;

import jakarta.validation.constraints.NotBlank;

public record DeviceCreateRequest(
        @NotBlank String name,
        @NotBlank String serialNumber
) {}