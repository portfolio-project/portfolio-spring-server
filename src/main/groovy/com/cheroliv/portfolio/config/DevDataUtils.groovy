package com.cheroliv.portfolio.config

import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.entity.PortfolioEntity
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.apache.commons.lang3.StringUtils

import java.time.ZoneId
import java.time.ZonedDateTime

import static com.cheroliv.portfolio.config.ApplicationConstants.ZERO_CHAR

@Slf4j
@Singleton
@CompileStatic
class DevDataUtils {


    static final Portfolio unsavedPortfolio = new Portfolio(
            name: 'new portfolio')

    public static final List<Portfolio> EMPTY_PORTFOLIOS = []
    public static final Collection PORTFOLIO_DATA = [
            [
                    id       : UUID.randomUUID(),
                    name     : "name1",
                    createdAt: ZonedDateTime.now(ZoneId.systemDefault()).minusDays(6),
                    updatedAt: ZonedDateTime.now().minusDays(5)
            ],
            [
                    id       : UUID.randomUUID(),
                    name     : "name2",
                    createdAt: ZonedDateTime.now().minusDays(4),
                    updatedAt: ZonedDateTime.now().minusDays(3)
            ],
            [
                    id       : UUID.randomUUID(),
                    name     : "name3",
                    createdAt: ZonedDateTime.now().minusDays(2),
                    updatedAt: ZonedDateTime.now().minusDays(1)
            ]
    ]
    public static final String JSON_EMPTY_RESULT = "[]"

    static final List<Portfolio> collectionToPortfolios(Collection datas) {
        datas.collect { map ->
            new Portfolio(
                    id: map.getAt('id') as UUID,
                    name: map.getAt('name') as String,
                    createdAt: map['createdAt'] as ZonedDateTime,
                    updatedAt: map['updatedAt'] as ZonedDateTime)
        }
    }

    static final List<PortfolioEntity> collectionToPortfolioEntities(Collection<Portfolio> datas) {
        collectionToPortfolios(datas).collect { Portfolio it ->
            PortfolioEntity.fromDto(it)
        }
    }

    static final String deleteZerosAtTheEndOfString(String s) {
        while (s.toCharArray().toList().last() == ZERO_CHAR) {
            s = StringUtils.chop(s)
        }
        s
    }
}
