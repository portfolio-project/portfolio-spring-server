package com.cheroliv.portfolio.entity

import com.cheroliv.portfolio.domain.Portfolio
import groovy.transform.ToString
import groovy.transform.TypeChecked
import org.hibernate.annotations.DynamicUpdate

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Index
import javax.persistence.SequenceGenerator
import javax.persistence.Table
import javax.validation.constraints.Size

import static javax.persistence.GenerationType.SEQUENCE


@Entity
@ToString
@TypeChecked
@DynamicUpdate
@Table(name = "`portfolio`", indexes = [
        @Index(name = "`idx_portfolio_name`", columnList = "`name`")])
class PortfolioEntity implements PortfolioEntityGeneric<Long> {
    @Id
    @GeneratedValue(strategy = SEQUENCE,
            generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "`id`")
    Long id
    @Size(max = 255)
    @Column(name = "`name`",
            length = 255)
    String name


    Portfolio toDto() {
        new Portfolio(id: id, name: name)
    }

    static PortfolioEntity fromDto(Portfolio dto) {
        new PortfolioEntity(id: dto.id, name: dto.name)
    }

    static Portfolio fromEntity(PortfolioEntity p) {
        new Portfolio(id: p.id, name: p.name)
    }
}
