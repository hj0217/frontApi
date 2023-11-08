package com.demo1.demo1;


import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@ConfigurationProperties(prefix = "spring.datasource.hikari")
@MapperScan(value = "com.demo1.demo1.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
@RequiredArgsConstructor
public class SpringConfig {

    @Bean
    public HikariDataSource dataSource()  {
        final HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:oracle:thin:@localhost:1521/xe");
        hikariDataSource.setUsername("System");
        hikariDataSource.setPassword("1234");
        return hikariDataSource;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
