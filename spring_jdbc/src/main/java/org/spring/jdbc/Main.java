package org.spring.jdbc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class Main {

    public static void main(String args[]){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Main.class, DaoConfig.class);

        VehicleDao vehicleDao = (VehicleDao) context.getBean("vehicleDao");

        Vehicle vehicle = new Vehicle("TEM0001","Red",4,4);

        vehicleDao.insert(vehicle);

        vehicle=vehicleDao.findByVehicleNo("TEM0001");
        System.out.println("Vehicle No: "+vehicle.getVehicleNo());
        System.out.println("Color: "+vehicle.getColor());
        System.out.println("Wheel: "+vehicle.getWheel());
        System.out.println("Seat: "+vehicle.getSeat());
    }

    @Configuration
    @Import(VehicleConfiguration.class)
    public class DaoConfig{
        @Bean
        public VehicleDao vehicleDao(DataSource dataSource){
            return new PlainJdbcVehicleDao(dataSource);
        }

    }
}
