package com.cheroliv.portfolio.entity.dao

import com.cheroliv.portfolio.entity.PortfolioEntity
import groovy.transform.TypeChecked
import org.springframework.data.repository.PagingAndSortingRepository

@TypeChecked
interface PortfolioDao extends PagingAndSortingRepository<PortfolioEntity, Long> {
}
