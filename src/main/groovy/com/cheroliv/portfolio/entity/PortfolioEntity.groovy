package com.cheroliv.portfolio.entity


import com.cheroliv.portfolio.domain.Portfolio
import groovy.transform.ToString
import groovy.transform.TypeChecked
import org.hibernate.annotations.DynamicUpdate

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

import static com.cheroliv.portfolio.config.ApplicationConstants.ND_NOTNULL_CSTRT_TPL_MSG
import static com.cheroliv.portfolio.config.ApplicationConstants.ND_SIZE_CSTRT_TPL_MSG
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
