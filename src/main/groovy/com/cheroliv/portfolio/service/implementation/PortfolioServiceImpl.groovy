package com.cheroliv.portfolio.service.implementation

import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.repository.PortfolioRepository
import com.cheroliv.portfolio.service.PortfolioService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Component
@TypeChecked
@Transactional(readOnly = true)
class PortfolioServiceImpl implements PortfolioService {

    final PortfolioRepository portfolioRepository

    PortfolioServiceImpl(PortfolioRepository portfolioRepository) {
        this.portfolioRepository = portfolioRepository
    }

    @Override
    List<Portfolio> getAll() {
        portfolioRepository.findAll()
    }

    @Override
    Portfolio save(Portfolio portfolio) {
        log.info("Portfolio save(@Valid Portfolio portfolio) throws ConstraintViolationException")
        portfolioRepository.save(portfolio)
    }
}
