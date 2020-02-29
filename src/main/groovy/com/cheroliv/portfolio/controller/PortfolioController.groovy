package com.cheroliv.portfolio.controller


import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.service.PortfolioService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@Slf4j
@TypeChecked
@RestController
@RequestMapping(value = PORTFOLIO_BASE_URL_REST_API,
        produces = [APPLICATION_JSON_VALUE])
class PortfolioController {
    public static final String PORTFOLIO_BASE_URL_REST_API = '/api/portfolios'
    public static final String JSON_EMPTY_RESULT = "[]"
    public static final List<Portfolio> EMPTY_PORTFOLIOS = []

    final PortfolioService portfolioService

    PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService
    }

    @GetMapping
    List<Portfolio> getAll() {
        this.portfolioService.getAll()
    }

}

