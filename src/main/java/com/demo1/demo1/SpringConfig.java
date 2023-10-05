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
//  private final EntityManager em;


//   public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//   }

//    @Bean
//    public TermService termService() {
//        return new TermService(jdbcTermRepository());
//    }

    @Bean
    public JdbcTermRepository jdbcTermRepository() {
        return new JdbcTermRepository(dataSource);
    }

}
