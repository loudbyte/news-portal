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
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;
import java.util.logging.Logger;

@Configuration
@PropertySource("classpath:persistence-oracle.properties")
@ComponentScan(basePackages = "com.epam.portal")
@EnableWebMvc
@EnableAspectJAutoProxy
public class SpringApplicationConfig extends WebSecurityConfigurerAdapter {

//    @Bean(name = "mySessionFactory")
//    public SessionFactory sessionFactory() {
//        return new org.hibernate.cfg.Configuration()
//                .configure("hibernate.cfg.xml")
//                .addAnnotatedClass(News.class)
//                .buildSessionFactory();
//    }

    @Autowired
    private Environment env;

    private Logger logger = Logger.getLogger(getClass().getName());


    @Bean
    public DataSource securityDataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driver"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        dataSource.setUsername(env.getProperty("jdbc.user"));
        dataSource.setPassword(env.getProperty("jdbc.password"));
        return dataSource;
    }

//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        final LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
//        entityManagerFactoryBean.setDataSource(securityDataSource());
//        entityManagerFactoryBean.setPackagesToScan("com.epam.portal.entity");
//        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
//        entityManagerFactoryBean.setJpaProperties(additionalProperties());
//        return entityManagerFactoryBean;
//    }
//
//    final Properties additionalProperties() {
//        final Properties hibernateProperties = new Properties();
//        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));
//        hibernateProperties.setProperty("hibernate.cache.use_second_level_cache", env.getProperty("hibernate.cache.use_second_level_cache"));
//        hibernateProperties.setProperty("hibernate.cache.use_query_cache", env.getProperty("hibernate.cache.use_query_cache"));
////         hibernateProperties.setProperty("hibernate.globally_quoted_identifiers", "true");
//        return hibernateProperties;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager(final EntityManagerFactory emf) {
//        final JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(emf);
//        return transactionManager;
//    }
//    @Bean
//    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
//        return new PersistenceExceptionTranslationPostProcessor();
//    }

    // need a helper method
    // read environment property and convert to int

    private int getIntProperty(String propName) {

        String propVal = env.getProperty(propName);

        // now convert to int
        int intPropVal = Integer.parseInt(propVal);

        return intPropVal;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // use jdbc authentication ... oh yeah!!!

        auth.jdbcAuthentication().dataSource(securityDataSource());

    }


//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//		// add our users for in memory authentication
//
//		User.UserBuilder users = User.withDefaultPasswordEncoder();
//
//		auth.inMemoryAuthentication()
//				.withUser(users.username("john").password("test123").roles("EMPLOYEE"))
//				.withUser(users.username("mary").password("test123").roles("MANAGER"))
//				.withUser(users.username("susan").password("test123").roles("ADMIN"));
//	}




    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/news")
//                .permitAll()
                .hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.GET, "/api/news/**")
//                .permitAll()
                .hasRole("EMPLOYEE")
                .antMatchers(HttpMethod.POST, "/api/news")
//                .permitAll()
                .hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/api/news/**")
//                .permitAll()
                .hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/news")
//                .permitAll()
                .hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/news/**")
//                .permitAll()
                .hasAnyRole("MANAGER", "ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/news/**")
//                .permitAll()
                .hasRole("ADMIN")
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();

    }


}
