package dev.markrz0.assetmanagement.model.dto;

import dev.markrz0.assetmanagement.model.entity.enums.DeviceStatus;

public record DeviceResponse(
        Long id,
        String name,
        String serialNumber,
        DeviceStatus status
) {}