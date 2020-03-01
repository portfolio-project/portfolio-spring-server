package com.cheroliv.portfolio


import org.springframework.context.ApplicationContext
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean

import javax.persistence.EntityManager

class TestUtils {

    static EntityManager getEntityManager(ApplicationContext applicationContext) {
        applicationContext
                .getBean(LocalContainerEntityManagerFactoryBean)
                .getNativeEntityManagerFactory()
                .createEntityManager()
    }
}
