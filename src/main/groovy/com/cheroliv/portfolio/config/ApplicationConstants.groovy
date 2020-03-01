package com.cheroliv.portfolio.config

final class ApplicationConstants {
    public static final String CORS_EXPOSED_HEADERS = 'Authorization,Link,X-Total-Count'
    public static final String API_URL_PREFIX_ACCEPTED_EXPRESSION = "/api/**"
    public static final String MANAGEMENT_URL_EXPRESSION = "/management/**"
    public static final String API_DOC_URL = "/v1/api-docs"
    public static final String CORS_ALLOWED_ORIGINS = '*'
    public static final String CORS_ALLOWED__METHODS = '*'
    public static final String CORS_ALLOWED_HEADERS = '*'
    static final String NAME_NOTNULL_CSTRT_TPL_MSG = '{com.cheroliv.portfolio.domain.nd.notnull.message}'
    static final String NAME_SIZE_CSTRT_TPL_MSG = '{com.cheroliv.portfolio.domain.nd.size.message}'

}
