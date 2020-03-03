package com.cheroliv.portfolio.domain

import groovy.transform.CompileStatic
import groovy.transform.ToString

import java.time.ZonedDateTime

@ToString
@CompileStatic
class Portfolio {
    UUID id
    String name
    ZonedDateTime createdAt
    ZonedDateTime updatedAt
}
