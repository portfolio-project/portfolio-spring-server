package com.cheroliv.portfolio.repository

import com.cheroliv.portfolio.domain.Portfolio

interface PortfolioRepository {
    List<Portfolio> findAll()

    Portfolio save(Portfolio portfolio)
}
