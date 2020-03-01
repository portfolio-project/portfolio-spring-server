package com.cheroliv.portfolio.repository.implementation

import com.cheroliv.portfolio.config.ApplicationConstants
import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.entity.PortfolioEntity
import com.cheroliv.portfolio.entity.dao.PortfolioDao
import com.cheroliv.portfolio.repository.PortfolioRepository
import groovy.transform.TypeChecked
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

import static com.cheroliv.portfolio.config.ApplicationConstants.CLASS_FIELD_UPDATED_AT


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
        this.portfolioDao
                .findAll(Sort.by(Sort.Direction.DESC, CLASS_FIELD_UPDATED_AT))
                .collect { PortfolioEntity it ->
                    it.toDto()
                }
    }
}
