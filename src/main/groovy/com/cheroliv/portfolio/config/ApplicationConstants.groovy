package com.cheroliv.portfolio.config

final class ApplicationConstants {
    /**
     * Misc
     */
    public static final String ZERO_CHAR = '0'

    /**
     * Controller
     */
    public static final String PORTFOLIO_BASE_URL_REST_API = '/api/portfolios'

    /**
     * Class fields names
     */
    public static final String CLASS_FIELD_UPDATED_AT = "updatedAt"
    public static final String CLASS_FIELD_ID = "id"
    public static final String CLASS_FIELD_CREATED_AT = "createdAt"
    public static final String CLASS_FIELD_NAME = "name"
    /**
     * CORS
     */
    public static final String CORS_EXPOSED_HEADERS = 'Authorization,Link,X-Total-Count'
    public static final String API_URL_PREFIX_ACCEPTED_EXPRESSION = "/api/**"
    public static final String MANAGEMENT_URL_EXPRESSION = "/management/**"
    public static final String API_DOC_URL = "/v1/api-docs"
    public static final String CORS_ALLOWED_ORIGINS = '*'
    public static final String CORS_ALLOWED__METHODS = '*'
    public static final String CORS_ALLOWED_HEADERS = '*'
    /**
     * BeanValidation
     */
    static final String NAME_NOTNULL_CSTRT_TPL_MSG = '{com.cheroliv.portfolio.domain.name.notnull.message}'
    static final String NAME_SIZE_CSTRT_TPL_MSG = '{com.cheroliv.portfolio.domain.name.size.message}'

}
