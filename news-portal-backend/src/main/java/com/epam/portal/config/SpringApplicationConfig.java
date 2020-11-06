package com.epam.portal.config;

import com.epam.portal.entity.News;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:persistence-oracle.properties")
@ComponentScan(basePackages = "com.epam.portal")
@EnableWebMvc
@EnableAspectJAutoProxy
public class SpringApplicationConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public SessionFactory sessionFactory() {
        final SessionFactory sessionFactory = new org.hibernate.cfg.Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(News.class)
                .buildSessionFactory();
        return sessionFactory;
    }

    @Autowired
    private Environment environment;

    @Bean
    public DataSource securityDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getProperty("jdbc.driver"));
        dataSource.setUrl(environment.getProperty("jdbc.url"));
        dataSource.setUsername(environment.getProperty("jdbc.user"));
        dataSource.setPassword(environment.getProperty("jdbc.password"));
        return dataSource;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(securityDataSource());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO do not use roles
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/news")
                .hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/news/**")
                .hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/api/news")
                .hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/news/**")
                .hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/news/**")
                .hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/news")
                .hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}