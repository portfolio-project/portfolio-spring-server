package com.cheroliv.portfolio.service

import com.cheroliv.portfolio.domain.Portfolio

interface PortfolioService {
    List<Portfolio> getAll()
}