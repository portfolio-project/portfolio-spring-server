package com.cheroliv.portfolio.controller.exceptions
//@ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Portfolio Name Exceeds Size")
class PortfolioNameExceedsSizeException extends RuntimeException {
    final String message
    final Throwable cause
}