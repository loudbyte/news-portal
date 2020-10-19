package com.epam.portal.config;

import com.epam.portal.entity.News;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan(basePackages = "com.epam.portal")
@EnableJpaRepositories(basePackages = "com.epam.portal")
@EnableWebMvc
public class SpringApplicationConfig {

//    @Bean(name = "mySessionFactory")
//    public SessionFactory sessionFactory() {
//        return new org.hibernate.cfg.Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(News.class)
//                .buildSessionFactory();
//    }

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.driver-class-name}")
    private String driver;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.password}")
    private String pass;

    @Bean
    public DataSource dataSource() {

        final DriverManagerDataSource dataSource = new DriverManagerDataSource();

        dataSource.setDriverClassName(driver);

        dataSource.setUrl(url);

        dataSource.setUsername(user);

        dataSource.setPassword(pass);

        return dataSource;

    }

    @Value("${spring.jpa.hibernate.ddl-auto}")
    String ddl;

    @Value("${spring.jpa.database-platform}")
    String platform;

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();

        entityManagerFactoryBean.setDataSource(dataSource());

        entityManagerFactoryBean.setPackagesToScan("");

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", ddl);
        properties.setProperty("hibernate.dialect", platform);

        entityManagerFactoryBean.setJpaProperties(properties);

        return entityManagerFactoryBean;

    }

}
