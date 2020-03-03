package com.cheroliv.portfolio.controller

import com.cheroliv.portfolio.controller.exceptions.PortfolioNameExceedsSizeException
import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.entity.PortfolioEntity
import com.cheroliv.portfolio.service.PortfolioService
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.MvcResult

import javax.validation.ConstraintViolationException
import javax.validation.Validation
import javax.validation.Validator
import javax.validation.ValidatorFactory
import java.time.ZonedDateTime

import static com.cheroliv.portfolio.config.ApplicationConstants.*
import static com.cheroliv.portfolio.config.DevDataUtils.*
import static org.hamcrest.Matchers.is
import static org.hamcrest.collection.IsCollectionWithSize.hasSize
import static org.mockito.BDDMockito.given
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

/*
name:"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"
*/

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


    static Validator validator

    @BeforeAll
    static void setUpValidator() {
        ValidatorFactory factory = Validation
                .buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }


    @Test
    @Order(5)
    @DisplayName('test_post_retrieve_the_portfolio_name_from_post_request')
    void test_post_retrieve_the_portfolio_name_from_post_request() {
        Portfolio portfolio = new Portfolio(name: "foo")
        mockMvc.perform(post(PORTFOLIO_BASE_URL_REST_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .header(HTTP_HEADER_OFFSET,
                        ZonedDateTime.now().offset.toString())
                .header(HTTP_HEADER_THE_TIMEZONE_IANA,
                        ZonedDateTime.now().zone.toString())
                .content(JsonOutput.toJson(portfolio)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath(CLASS_FIELD_NAME, is(portfolio.name)))
                .andReturn()
    }

    @Test
    @Order(6)
    @DisplayName('test_mocked_request_contains_zoneid_and_offset')
    void test_mocked_request_contains_zoneid_and_offset() {

        given(portfolioService.getAll())
                .willReturn(collectionToPortfolios(PORTFOLIO_DATA).reverse())

        MvcResult result = mockMvc.perform(get(PORTFOLIO_BASE_URL_REST_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .header(HTTP_HEADER_OFFSET,
                        ZonedDateTime.now().offset.toString())
                .header(HTTP_HEADER_THE_TIMEZONE_IANA,
                        ZonedDateTime.now().zone.toString()))
                .andExpect(status().isOk())
                .andReturn()

        assert result.request.headerNames.toList()
                .toListString()
                .contains(HTTP_HEADER_THE_TIMEZONE_IANA)

        assert result.request.headerNames.toList()
                .toListString().contains(HTTP_HEADER_OFFSET)
    }


//    @Test
//    @Order(6)
//    @DisplayName('test_post_with_portfolio_name_is_null')
//    void test_post_with_portfolio_name_is_null() {
//        Portfolio portfolio = new Portfolio(name: null)
//        mockMvc.perform(post(PORTFOLIO_BASE_URL_REST_API)
//                .contentType(APPLICATION_JSON)
//                .accept(APPLICATION_JSON)
//                .content(JsonOutput.toJson(portfolio)))
//                .andExpect(status().isCreated())
//                .andExpect(jsonPath(CLASS_FIELD_NAME, is(portfolio.name)))
//                .andReturn()
//    }


    @Test
    @Order(4)
    @Disabled
    @DisplayName("test_save_name_exceeds_size")
    void test_save_name_exceeds_size() throws PortfolioNameExceedsSizeException {
        String name = ""
        300.times {
            name = name + "a"
        }
        assert name.length() > 255
        Portfolio portfolio = new Portfolio(name: name)

        log.info(JsonOutput.toJson(portfolio))


        given(portfolioService.save(portfolio))
                .willThrow(new ConstraintViolationException(validator.validateProperty(
                        PortfolioEntity.fromDto(portfolio), CLASS_FIELD_NAME)))

//        when(portfolioService.save(portfolio)).thenThrow(new ConstraintViolationException(validator.validateProperty(
//                PortfolioEntity.fromDto(portfolio), CLASS_FIELD_NAME)))

        portfolioService.save(portfolio)


        MvcResult mvcResult = mockMvc.perform(post(PORTFOLIO_BASE_URL_REST_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(JsonOutput.toJson(portfolio)))
                .andExpect(status().isUnprocessableEntity())
                .andReturn()
//        assert result.resolvedException instanceof PortfolioNameExceedsSizeException


        JsonSlurper jsonSlurper = new JsonSlurper()
        Object object = jsonSlurper.parseText(mvcResult.response.contentAsString)
        assert object instanceof Map
        assert object.name == portfolio.name
        assert object.id != null
    }

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


        MvcResult result = mockMvc.perform(get(PORTFOLIO_BASE_URL_REST_API))
                .andExpect(status().isOk())
                .andExpect(jsonPath("\$", hasSize(3)))
                .andExpect(jsonPath("\$[0]$CLASS_FIELD_ID",
                        is(expectedDatas.first().id.toString())))
                .andExpect(jsonPath("\$[0]$CLASS_FIELD_NAME",
                        is(expectedDatas.first().name)))
                .andExpect(jsonPath("\$[1]$CLASS_FIELD_ID",
                        is(expectedDatas[1].id.toString())))
                .andExpect(jsonPath("\$[1]$CLASS_FIELD_NAME",
                        is(expectedDatas[1].name)))
                .andExpect(jsonPath("\$[2]$CLASS_FIELD_ID",
                        is(expectedDatas[2].id.toString())))
                .andExpect(jsonPath("\$[2]$CLASS_FIELD_NAME",
                        is(expectedDatas[2].name)))
                .andReturn()

        Object portfolios = new JsonSlurper().parseText(result.response.contentAsString)

        assert ZonedDateTime.parse((portfolios as Collection<Portfolio>)[0]
                .createdAt.toString()).toEpochSecond() ==
                expectedDatas.first().createdAt.toEpochSecond()
        assert ZonedDateTime.parse((portfolios as Collection<Portfolio>)[0]
                .updatedAt.toString()).toEpochSecond() ==
                expectedDatas.first().updatedAt.toEpochSecond()

        assert ZonedDateTime.parse((portfolios as Collection<Portfolio>)[1]
                .createdAt.toString()).toEpochSecond() ==
                expectedDatas.get(1).createdAt.toEpochSecond()
        assert ZonedDateTime.parse((portfolios as Collection<Portfolio>)[1]
                .updatedAt.toString()).toEpochSecond() ==
                expectedDatas.get(1).updatedAt.toEpochSecond()

        assert ZonedDateTime.parse((portfolios as Collection<Portfolio>)[2]
                .createdAt.toString()).toEpochSecond() ==
                expectedDatas.get(2).createdAt.toEpochSecond()
        assert ZonedDateTime.parse((portfolios as Collection<Portfolio>)[2]
                .updatedAt.toString()).toEpochSecond() ==
                expectedDatas.get(2).updatedAt.toEpochSecond()

    }


//
//    @Test
//    @Order(4)
//    @DisplayName("test_save_name_exceeds_size")
//    void test_save_id_already_exists_before_save()
//            throws PortfolioIdAlreadyExistsBeforeSaveException {
//        given(interService.save(data.firstInterDto))
//                .willReturn(null)
//        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(InterController.INTER_BASE_URL_REST_API)
//                .contentType(APPLICATION_JSON)
//                .with(csrf().asHeader())
//                .accept(APPLICATION_JSON)
//                .content(data.jsonFirstInterDto))
//                .andExpect(status().isUnprocessableEntity())
//                .andReturn()
//        assert result.resolvedException instanceof
//                PortfolioIdAlreadyExistsBeforeSaveException
//    }
//
//    @Test
//    @Order(5)
//    @DisplayName("test_save")
//    void test_save() {
//        given(interService.save(data.newInterDto))
//                .willReturn(data.expectedPersistedInterDto)
//        given(this.interService.isUniqueIndexAvailable(
//                anyString(), anyString())).willReturn(true)
//        mockMvc.perform(MockMvcRequestBuilders.post(InterController.INTER_BASE_URL_REST_API)
//                .with(csrf().asHeader())
//                .contentType(APPLICATION_JSON)
//                .accept(APPLICATION_JSON)
//                .content(data.jsonNewtInterDto))
//                .andExpect(status().isCreated())
//                .andExpect(content().contentType(
//                        APPLICATION_JSON_UTF8_VALUE))
//                .andDo(print())
//                .andExpect(jsonPath("nd")
//                        .value(data.newInterDto.nd))
//                .andExpect(jsonPath("typeInter")
//                        .value(data.newInterDto.typeInter))
//                .andExpect(jsonPath("firstName")
//                        .value(data.newInterDto.firstName))
//                .andExpect(jsonPath("lastName")
//                        .value(data.newInterDto.lastName))
//                .andReturn()
//    }


}
