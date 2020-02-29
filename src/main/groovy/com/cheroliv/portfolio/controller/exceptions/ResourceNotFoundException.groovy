package com.cheroliv.portfolio.controller.exceptions


import org.springframework.web.bind.annotation.ResponseStatus

import static org.springframework.http.HttpStatus.NOT_FOUND

@ResponseStatus(code = NOT_FOUND, reason = "Resources Not Found")
class ResourceNotFoundException extends RuntimeException {
}