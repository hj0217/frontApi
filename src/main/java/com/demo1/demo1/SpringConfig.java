package com.demo1.demo1;

import com.demo1.demo1.repository.JdbcTermRepository;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;

import org.mybatis.spring.annotation.MapperScan;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource")
@MapperScan(value= "com.demo1.demo1.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
@RequiredArgsConstructor
public class SpringConfig extends HikariConfig {

    //private final DataSource dataSource;

//    @Bean
//    public TermService termService() {
//        return new TermService(termRepository());
//    }
//
//    @Bean
//    public JdbcTermRepository termRepository() {
//        return new JdbcTermRepository(dataSource());
//        //return new JdbcTermRepository(em);
//    }

    @Bean
    public DataSource dataSource() {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/xe");
        hikariDataSource.setUsername("System");
        hikariDataSource.setPassword("1234");
        return hikariDataSource;
    }

}
