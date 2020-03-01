package com.cheroliv.portfolio


import com.cheroliv.portfolio.domain.Portfolio
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

@Slf4j
@Singleton
@CompileStatic
class TestData {

    public static final List<Portfolio> EMPTY_PORTFOLIOS = []
    public static final Collection PORTFOLIO_DATA = [
            [id: 1, name: "name1"],
            [id: 2, name: "name2"],
            [id: 3, name: "name3"]
    ]
    public static final String JSON_EMPTY_RESULT = "[]"

    static final List<Portfolio> collectionToPortfolios(Collection datas) {
        datas.collect { map ->
            new Portfolio(
                    id: map.getAt('id') as Long,
                    name: map.getAt('name') as String)
        }
    }
}
