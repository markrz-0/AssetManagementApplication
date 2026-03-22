package dev.markrz0.assetmanagement.model.dto;

import java.time.LocalDateTime;

public record CheckoutResponse(
        Long checkoutId,
        String employeeName,
        String deviceName,
        String deviceSerialNumber,
        LocalDateTime checkoutDate,
        LocalDateTime returnDate
) {}