package org.spring.jdbc.config;


import com.mysql.jdbc.Driver;
import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class MainConfiguration {

    @Bean
    public MainDao commentDao(JdbcTemplate jdbcTemplate){
        return new CommentServiceImpl(jdbcTemplate);
    }

    @Bean
    public MainDao donationDao(JdbcTemplate jdbcTemplate){
        return new DonationServiceImpl(jdbcTemplate);
    }

    @Bean
    public MainDao serviceEventDao(JdbcTemplate jdbcTemplate){
        return new ServiceEventServiceImpl(jdbcTemplate);
    }

    @Bean
    public MainDao userDao(JdbcTemplate jdbcTemplate){
        return new UserServiceImpl(jdbcTemplate);
    }

    @Bean
    public MainDao toolDao(JdbcTemplate jdbcTemplate){
        return new ToolServiceImpl(jdbcTemplate);
    }

    @Bean
    public MainDao skillDao(JdbcTemplate jdbcTemplate){
        return new SkillServiceImpl(jdbcTemplate);
    }

    @Bean
    public MainDao communityDao(JdbcTemplate jdbcTemplate){
        return new CommunityServiceImpl(jdbcTemplate);
    }

    @Bean
    public MainDao projectDao(JdbcTemplate jdbcTemplate){
        return new ProjectServiceImpl(jdbcTemplate);
    }


    @Bean
    public MainService mainDao(JdbcTemplate jdbcTemplate){
        return new MainService(jdbcTemplate);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }

    @Bean
    public DataSource simlpeDataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://"+System.getenv("MSQL_HOST")+":3306/springjdbc_home");
        dataSource.setUsername(System.getenv("MSQL_USER"));
        dataSource.setPassword(System.getenv("MSQL_FUNC"));
        return  dataSource;
    }
}
