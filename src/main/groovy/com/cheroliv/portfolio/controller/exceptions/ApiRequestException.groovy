package com.cheroliv.portfolio.controller.exceptions

class ApiRequestException extends RuntimeException {
    final String message
    final Throwable cause
}