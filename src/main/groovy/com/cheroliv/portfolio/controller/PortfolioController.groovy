package com.cheroliv.portfolio.controller


import com.cheroliv.portfolio.domain.Portfolio
import com.cheroliv.portfolio.service.PortfolioService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import java.nio.charset.StandardCharsets
import java.time.LocalDateTime
import java.time.ZonedDateTime

import static com.cheroliv.portfolio.config.ApplicationConstants.PORTFOLIO_BASE_URL_REST_API
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE

@Slf4j
@TypeChecked
@RestController
@RequestMapping(value = PORTFOLIO_BASE_URL_REST_API,
        produces = [APPLICATION_JSON_VALUE])
class PortfolioController {

    final PortfolioService portfolioService

    PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService
    }

    @GetMapping
    List<Portfolio> getAll() {
        this.portfolioService.getAll()
    }

//
//    @PostMapping(consumes = APPLICATION_JSON_VALUE)
//    ResponseEntity<Portfolio> save(@RequestBody Portfolio portfolio)
//            throws URISyntaxException,
//                    PortfolioNameExceedsSizeException,
//                    PortfolioIdAlreadyExistsBeforeSaveException {
//
//        if (portfolio.getId() != null)
//            throw new PortfolioIdAlreadyExistsBeforeSaveException()
//
//
//        try {
//            log.info("\n\n\n\n\n\nDans le catch\non passe ici!!!!!!!!!!!!!!!!!!")
//            portfolio = portfolioService.save(portfolio)
//        } catch (ValidationException ve) {
//            log.info("\n\n\n\n\n\non passe ici!!!!!!!!!!!!!!!!!!")
//            throw new PortfolioNameExceedsSizeException(
//                    cause: ve,
//                    message: ve.message)
//        }
////        log.info("\n\n\n\n\n\napres le catch on passe ici!!!!!!!!!!!!!!!!!!")
//
//        String uri = "$PORTFOLIO_BASE_URL_REST_API/${portfolio.id}"
////        String uri = "$PORTFOLIO_BASE_URL_REST_API/${UUID.randomUUID()}"
//        ResponseEntity.created(new URI(uri))
//                .headers(createEntityCreationAlert(
//                        'springPortfolio',
//                        true,
//                        ENTITY_NAME,
//                        portfolio.id.toString()))
//                .body(portfolio)
//    }

    Portfolio createPortfolio(Portfolio portfolio) {
        new Portfolio(
                id: UUID.randomUUID(),
                name: portfolio.name,
                createdAt: LocalDateTime.now(),
                updatedAt: LocalDateTime.now())
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    ResponseEntity<Portfolio> save(@RequestBody Portfolio portfolio) {
        portfolio = createPortfolio(portfolio)
        String uri = "${PORTFOLIO_BASE_URL_REST_API}/${portfolio.id}"
        ResponseEntity
                .created(new URI(uri))
                .headers(createEntityCreationAlert(
                        'springPortfolio',
                        true,
                        ENTITY_NAME,
                        portfolio.id.toString()))
                .body(portfolio)
    }

    private static final String ENTITY_NAME = "portfolio";

    static HttpHeaders createEntityCreationAlert(
            String applicationName,
            boolean enableTranslation,
            String entityName,
            String param) {
        String message = enableTranslation ? applicationName + "." + entityName + ".created"
                : "A new " + entityName + " is created with identifier " + param;
        return createAlert(applicationName, message, param);
    }

    static HttpHeaders createAlert(String applicationName, String message, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("X-" + applicationName + "-alert", message);
        try {
            headers.add("X-" + applicationName + "-params", URLEncoder.encode(param, StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException e) {
            // StandardCharsets are supported by every Java implementation so this exception will never happen
        }
        return headers;
    }
}

