package com.cheroliv.portfolio.controller

import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.service.PortfolioService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult

import static com.cheroliv.portfolio.config.ApplicationConstants.CLASS_FIELD_CREATED_AT
import static com.cheroliv.portfolio.config.ApplicationConstants.CLASS_FIELD_ID
import static com.cheroliv.portfolio.config.ApplicationConstants.CLASS_FIELD_NAME
import static com.cheroliv.portfolio.config.ApplicationConstants.CLASS_FIELD_UPDATED_AT
import static com.cheroliv.portfolio.config.ApplicationConstants.PORTFOLIO_BASE_URL_REST_API
import static com.cheroliv.portfolio.config.DevDataUtils.*
import static org.hamcrest.Matchers.is
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Slf4j
@TypeChecked
@TestMethodOrder(OrderAnnotation)
@WebMvcTest(value = PortfolioController)
@DisplayName("PortfolioControllerUnitTest")
class PortfolioControllerUnitTest {

    @Autowired
    MockMvc mockMvc
    @MockBean
    PortfolioService portfolioService


    @Test
    @Order(1)
    @DisplayName("test_get_portfolios_with_empty_data")
    void test_get_portfolios_with_empty_data() {

        given(portfolioService.getAll())
                .willReturn(EMPTY_PORTFOLIOS)

        MvcResult result = mockMvc.perform(
                get(PORTFOLIO_BASE_URL_REST_API))
                .andExpect(status().isOk())
                .andReturn()
        assert result.response.contentAsString == JSON_EMPTY_RESULT
    }

    @Test
    @Order(2)
    @DisplayName("test_get_portfolios_with_malformed_url")
    void test_get_portfolios_with_malformed_url() {
        String malformed = "/eeeeeeeeeeeeeeeeeeeee"
        mockMvc.perform(
                get(PORTFOLIO_BASE_URL_REST_API + malformed))
                .andExpect(status().isNotFound())
                .andReturn()
    }


    @Test
    @Order(3)
    @DisplayName("test_get_portfolios_with_datas")
    void test_get_portfolios_with_datas() {

        List<Portfolio> expectedDatas =
                collectionToPortfolios(PORTFOLIO_DATA).reverse()

        given(portfolioService.getAll())
                .willReturn(expectedDatas)


        mockMvc.perform(get(PORTFOLIO_BASE_URL_REST_API))
                .andExpect(status().isOk())

                .andExpect(jsonPath("\$", hasSize(3)))

                .andExpect(jsonPath("\$[0]$CLASS_FIELD_ID",
                        is(expectedDatas.first().id.toString())))
                .andExpect(jsonPath("\$[0]$CLASS_FIELD_NAME",
                        is(expectedDatas.first().name)))
                .andExpect(jsonPath("\$[0]$CLASS_FIELD_CREATED_AT",
                        is(deleteZerosAtTheEndOfString(expectedDatas
                                .first()
                                .createdAt.toString()))))
                .andExpect(jsonPath("\$[0]$CLASS_FIELD_UPDATED_AT",
                        is(deleteZerosAtTheEndOfString(expectedDatas
                                .first()
                                .updatedAt.toString()))))

                .andExpect(jsonPath("\$[1]$CLASS_FIELD_ID",
                        is(expectedDatas[1].id.toString())))
                .andExpect(jsonPath("\$[1]$CLASS_FIELD_NAME",
                        is(expectedDatas[1].name)))
                .andExpect(jsonPath("\$[1]$CLASS_FIELD_CREATED_AT",
                        is(deleteZerosAtTheEndOfString(expectedDatas[1]
                                .createdAt.toString()))))
                .andExpect(jsonPath("\$[1]$CLASS_FIELD_UPDATED_AT",
                        is(deleteZerosAtTheEndOfString(expectedDatas[1]
                                .updatedAt.toString()))))

                .andExpect(jsonPath("\$[2]$CLASS_FIELD_ID",
                        is(expectedDatas[2].id.toString())))
                .andExpect(jsonPath("\$[2]$CLASS_FIELD_NAME",
                        is(expectedDatas[2].name)))
                .andExpect(jsonPath("\$[2]$CLASS_FIELD_CREATED_AT",
                        is(expectedDatas[2].createdAt.toString())))
                .andExpect(jsonPath("\$[2]$CLASS_FIELD_UPDATED_AT",
                        is(expectedDatas[2].updatedAt.toString())))

                .andReturn()
    }
}
