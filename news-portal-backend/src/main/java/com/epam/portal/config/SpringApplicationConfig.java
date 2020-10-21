package com.epam.portal.config;

import com.epam.portal.entity.News;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.epam.portal")
@EnableWebMvc
@EnableAspectJAutoProxy
public class SpringApplicationConfig {

    @Bean(name = "mySessionFactory")
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(News.class)
                .buildSessionFactory();
    }


}
