package com.cheroliv.portfolio

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@Slf4j
@SpringBootTest
@DisplayName('Hunt the canary!')
class PortfolioApplicationTests {

    @Test
    @DisplayName('today I shot the canary!')
    void contextLoads() {
        log.info('hi canary!!!!')
    }

}
