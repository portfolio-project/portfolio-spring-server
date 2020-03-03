package com.cheroliv.portfolio.entity

import java.time.ZonedDateTime

interface PortfolioEntityGeneric<ID> {

    ID getId()

    void setId(ID id)

    String getName()

    void setName(String name)

    ZonedDateTime getCreatedAt()

    void setCreatedAt(ZonedDateTime createdAt)

    ZonedDateTime getUpdatedAt()

    void setUpdatedAt(ZonedDateTime UpdatedAt)
}