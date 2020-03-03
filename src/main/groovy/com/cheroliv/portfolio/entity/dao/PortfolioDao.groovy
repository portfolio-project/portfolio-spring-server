package com.cheroliv.portfolio.entity.dao

import com.cheroliv.portfolio.entity.PortfolioEntity
import groovy.transform.CompileStatic
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
@CompileStatic
interface PortfolioDao extends PagingAndSortingRepository<PortfolioEntity, UUID> {
//interface PortfolioDao extends JpaRepository<PortfolioEntity, UUID> {

}
