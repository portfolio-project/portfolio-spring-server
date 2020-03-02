package com.cheroliv.portfolio.service

import com.cheroliv.portfolio.domain.Portfolio

import javax.validation.ConstraintViolationException

interface PortfolioService {
    List<Portfolio> getAll()

    Portfolio save(Portfolio portfolio) throws ConstraintViolationException
}