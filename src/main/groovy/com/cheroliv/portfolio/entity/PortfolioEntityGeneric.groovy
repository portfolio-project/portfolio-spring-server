package com.cheroliv.portfolio.entity

interface PortfolioEntityGeneric<ID> {

    ID getId()

    void setId(ID id)

    String getName()

    void setName(String name)
}