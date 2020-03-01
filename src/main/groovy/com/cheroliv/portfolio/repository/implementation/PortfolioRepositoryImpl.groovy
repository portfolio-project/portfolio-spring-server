package com.cheroliv.portfolio.repository.implementation

import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.entity.dao.PortfolioDao
import com.cheroliv.portfolio.repository.PortfolioRepository
import groovy.transform.TypeChecked
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional


@Repository
@TypeChecked
@Transactional
class PortfolioRepositoryImpl implements PortfolioRepository {
    final PortfolioDao portfolioDao

    PortfolioRepositoryImpl(PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao
    }

    @Override
    List<Portfolio> findAll() {
        []
    }
}
