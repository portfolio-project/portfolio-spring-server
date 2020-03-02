package com.cheroliv.portfolio.controller.exceptions

import org.springframework.http.HttpStatus

import java.time.ZonedDateTime

class ApiException {
    final String message
    final Throwable cause
    final HttpStatus httpStatus
    final ZonedDateTime timestamp
}
