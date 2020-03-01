package com.cheroliv.portfolio.config


import com.cheroliv.portfolio.entity.dao.PortfolioDao
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

import javax.annotation.PostConstruct

import static DevDataUtils.PORTFOLIO_DATA
import static DevDataUtils.collectionToPortfolioEntities

@Slf4j
@Component
@TypeChecked
@Transactional
@Profile("dev")
class DevProfileDatabaseInitializer {

    final PortfolioDao portfolioDao

    DevProfileDatabaseInitializer(
            PortfolioDao portfolioDao) {
        this.portfolioDao = portfolioDao
    }

    @PostConstruct
    void initializeDatabase() {
        log.info(this.class.simpleName + '.initializeDatabase()')
        insertDevDatas()
    }

    void insertDevDatas() {
        log.info(this.class.simpleName + '.insertDevDatas()')
        portfolioDao.saveAll(collectionToPortfolioEntities(PORTFOLIO_DATA))
    }
}
