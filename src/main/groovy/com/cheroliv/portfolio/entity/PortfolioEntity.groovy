package com.cheroliv.portfolio.entity

import com.cheroliv.portfolio.domain.Portfolio
import groovy.transform.ToString
import groovy.transform.TypeChecked
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import static com.cheroliv.portfolio.config.ApplicationConstants.ND_NOTNULL_CSTRT_TPL_MSG
import static com.cheroliv.portfolio.config.ApplicationConstants.ND_SIZE_CSTRT_TPL_MSG

@Entity
@ToString
@TypeChecked
@DynamicUpdate
@Table(name = "`portfolio`", indexes = [
        @Index(name = "`idx_portfolio_name`", columnList = "`name`")])
class PortfolioEntity implements PortfolioEntityGeneric<UUID> {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "`id`",
            updatable = false,
            nullable = false)
    UUID id

    @Size(min = 1, max = 255,
            message = ND_SIZE_CSTRT_TPL_MSG)
    @Column(name = "`name`",
            length = 255)
    @NotNull(message = ND_NOTNULL_CSTRT_TPL_MSG)
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

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof PortfolioEntity)) return false

        PortfolioEntity that = (PortfolioEntity) o

        if (id != that.id) return false
        if (name != that.name) return false

        return true
    }

    int hashCode() {
        int result
        result = (id != null ? id.hashCode() : 0)
        result = 31 * result + (name != null ? name.hashCode() : 0)
        return result
    }
}
