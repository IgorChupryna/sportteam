package org.spring.jdbc;


import com.mysql.jdbc.Driver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import javax.sql.DataSource;

@Configuration
public class VehicleConfiguration {

    @Bean
    public VehicleDao vehicleDao(JdbcTemplate jdbcTemplate){
        return new JdbcVehicalDaoImpl(jdbcTemplate);
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
    @Bean
    public DataSource simlpeDataSource(){
        SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(Driver.class);
        dataSource.setUrl("jdbc:mysql://localhost:3306/vehicledb");
        dataSource.setUsername("root");
        dataSource.setPassword("totoadmin");
        return  dataSource;
    }
}
