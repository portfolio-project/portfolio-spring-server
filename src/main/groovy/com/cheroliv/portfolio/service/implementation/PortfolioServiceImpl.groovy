package com.cheroliv.portfolio.service.implementation

import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.repository.PortfolioRepository
import com.cheroliv.portfolio.service.PortfolioService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Slf4j
@Service
@TypeChecked
@Transactional(readOnly = true)
class PortfolioServiceImpl implements PortfolioService{

    final PortfolioRepository portfolioRepository

    PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository
    }

    @Override
    List<Portfolio> getAll() {
        portfolioRepository.findAll()
    }
}
