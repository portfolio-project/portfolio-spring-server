package com.cheroliv.portfolio.controller.exceptions

import org.springframework.web.bind.annotation.ResponseStatus

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY

@ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "Portfolio Id Already Exists Before Save")
class PortfolioIdAlreadyExistsBeforeSaveException extends RuntimeException {
}

