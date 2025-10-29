package com.restaurant_backend.exception;

import java.time.LocalDateTime;

public record CustomErrorRecord(
        LocalDateTime dateTime,
        String message,
        String details
) {
}
