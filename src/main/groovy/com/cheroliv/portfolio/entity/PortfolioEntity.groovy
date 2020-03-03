package com.cheroliv.portfolio.entity

import com.cheroliv.portfolio.domain.Portfolio
import groovy.transform.ToString
import groovy.transform.TypeChecked
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.GenericGenerator

import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size
import java.time.LocalDateTime
import java.time.ZonedDateTime

import static com.cheroliv.portfolio.config.ApplicationConstants.NAME_NOTNULL_CSTRT_TPL_MSG
import static com.cheroliv.portfolio.config.ApplicationConstants.NAME_SIZE_CSTRT_TPL_MSG

@Entity
@ToString
@TypeChecked
@DynamicUpdate
@Table(name = "`portfolio`", indexes = [
        @Index(name = "`idx_portfolio_name`", columnList = "`name`"),
        @Index(name = "`idx_portfolio_created_at`", columnList = "`created_at`"),
        @Index(name = "`idx_portfolio_updated_at`", columnList = "`updated_at`"),])
class PortfolioEntity implements PortfolioEntityGeneric<UUID> {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "`id`",
            updatable = false,
            nullable = false)
    UUID id

    @Size(min = 1, max = 255, message = NAME_SIZE_CSTRT_TPL_MSG)
    @Column(name = "`name`", length = 255)
    @NotNull(message = NAME_NOTNULL_CSTRT_TPL_MSG)
    String name

    @NotNull
    @Column(name = "created_at"/*,columnDefinition = "TIMESTAMP"*/)
    ZonedDateTime createdAt

    @NotNull
    @Column(name = "updated_at"/*,columnDefinition = "TIMESTAMP"*/)
    ZonedDateTime updatedAt


    Portfolio toDto() {
        new Portfolio(
                id: id,
                name: name,
                createdAt: createdAt,
                updatedAt: updatedAt)
    }

    static PortfolioEntity fromDto(Portfolio dto) {
        new PortfolioEntity(
                id: dto.id,
                name: dto.name,
                createdAt: dto.createdAt,
                updatedAt: dto.updatedAt)
    }

    static Portfolio fromEntity(PortfolioEntity p) {
        new Portfolio(
                id: p.id,
                name: p.name,
                createdAt: p.createdAt,
                updatedAt: p.updatedAt)
    }

    boolean equals(o) {
        if (this.is(o)) return true
        if (!(o instanceof PortfolioEntity)) return false

        PortfolioEntity that = (PortfolioEntity) o

        if (createdAt != that.createdAt) return false
        if (id != that.id) return false
        if (name != that.name) return false
        if (updatedAt != that.updatedAt) return false

        return true
    }

    int hashCode() {
        int result
        result = id.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + createdAt.hashCode()
        result = 31 * result + updatedAt.hashCode()
        return result
    }
}
