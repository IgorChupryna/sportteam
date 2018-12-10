package org.spring.config;


import com.mysql.jdbc.Driver;
import org.spring.interfaces.UserService;
import org.spring.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class MainConfiguration {

    @Bean
    public UserService userServise(JdbcTemplate jdbcTemplate){
        return new UserServiceImpl(jdbcTemplate);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource simlpeDataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://"+System.getenv("MSQL_HOST")+":3306/springmvc_work");
        dataSource.setUsername(System.getenv("MSQL_USER"));
        dataSource.setPassword(System.getenv("MSQL_FUNC"));
        return  dataSource;
    }
}
