package com.cheroliv.portfolio.domain

import groovy.transform.CompileStatic
import groovy.transform.ToString

import java.time.LocalDateTime

@ToString
@CompileStatic
class Portfolio {
    UUID id
    String name
    LocalDateTime createdAt
    LocalDateTime updatedAt
}
