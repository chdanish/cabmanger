package com.RestSecureOath.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class JdbcConfiguration {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private LocalContainerEntityManagerFactoryBean entityManagerFactoryBean;

    @Bean
    public PlatformTransactionManager transactionManager() {
    	return new JpaTransactionManager(entityManagerFactoryBean.getObject());
        //return new DataSourceTransactionManager(dataSource);
    }    
    
    /*@Bean
    public EntityManager em() {
        return entityManagerFactoryBean.getObject().createEntityManager();
    } */

}
