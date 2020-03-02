package com.cheroliv.portfolio.controller.exceptions


import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.support.RequestContext

import java.time.ZonedDateTime

@ControllerAdvice
class ApiExceptionHandler implements ApplicationContextAware {

    ApplicationContext applicationContext

    @ExceptionHandler(value = [PortfolioNameExceedsSizeException])
    ResponseEntity<Object> handleApiRequestUnprocessableEntityException(
            ApiRequestException apiRequestException) {
        RequestContext requestContext = applicationContext.getBean(RequestContext)
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY
        ApiException apiException = new ApiException(
                message: apiRequestException.message,
                cause: apiRequestException.cause,
                timestamp: ZonedDateTime.now(requestContext.timeZone.toZoneId()),
                httpStatus: httpStatus
        )
        new ResponseEntity<Object>(apiException, httpStatus)
    }
}
