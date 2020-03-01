package com.cheroliv.portfolio.config


import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.entity.PortfolioEntity
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@Singleton
@CompileStatic
class DevData {

    public static final List<Portfolio> EMPTY_PORTFOLIOS = []
    public static final Collection PORTFOLIO_DATA = [
            [id: UUID.randomUUID(), name: "name1"],
            [id: UUID.randomUUID(), name: "name2"],
            [id: UUID.randomUUID(), name: "name3"]
    ]
    public static final String JSON_EMPTY_RESULT = "[]"

    static final List<Portfolio> collectionToPortfolios(Collection datas) {
        datas.collect { map ->
            new Portfolio(
                    id: map.getAt('id') as UUID,
                    name: map.getAt('name') as String)
        }
    }

    static final List<PortfolioEntity> collectionToPortfolioEntities(Collection datas) {
        collectionToPortfolios(datas).collect { it ->
            PortfolioEntity.fromDto(it)
        }
    }
}
