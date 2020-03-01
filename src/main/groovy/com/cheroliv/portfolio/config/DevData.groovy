package com.cheroliv.portfolio.config


import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.entity.PortfolioEntity
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.time.LocalDateTime

@Slf4j
@Singleton
@CompileStatic
class DevData {

    public static final List<Portfolio> EMPTY_PORTFOLIOS = []
    public static final Collection PORTFOLIO_DATA = [
            [
                    id       : UUID.randomUUID(),
                    name     : "name1",
                    createdAt: LocalDateTime.now().minusDays(6),
                    updatedAt: LocalDateTime.now().minusDays(5)
            ],
            [
                    id       : UUID.randomUUID(),
                    name     : "name2",
                    createdAt: LocalDateTime.now().minusDays(4),
                    updatedAt: LocalDateTime.now().minusDays(3)
            ],
            [
                    id       : UUID.randomUUID(),
                    name     : "name3",
                    createdAt: LocalDateTime.now().minusDays(2),
                    updatedAt: LocalDateTime.now().minusDays(1)
            ]
    ]
    public static final String JSON_EMPTY_RESULT = "[]"

    static final List<Portfolio> collectionToPortfolios(Collection datas) {
        datas.collect { map ->
            new Portfolio(
                    id: map.getAt('id') as UUID,
                    name: map.getAt('name') as String,
                    createdAt: map.getAt('createdAt') as LocalDateTime,
                    updatedAt: map.getAt('updatedAt') as LocalDateTime)
        }
    }

    static final List<PortfolioEntity> collectionToPortfolioEntities(Collection datas) {
        collectionToPortfolios(datas).collect { it ->
            PortfolioEntity.fromDto(it)
        }
    }
}
