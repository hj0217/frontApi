package com.demo1.demo1;

import com.demo1.demo1.repository.JdbcTermRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class SpringConfig {

    private final DataSource dataSource;

//    @Bean
//    public TermService termService() {
//        return new TermService(termRepository());
//    }

    @Bean
    public JdbcTermRepository termRepository() {
        return new JdbcTermRepository(dataSource);
        //return new JdbcTermRepository(em);
    }

}
