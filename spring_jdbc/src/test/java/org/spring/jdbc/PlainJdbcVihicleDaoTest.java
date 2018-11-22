package org.spring.jdbc;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PlainJdbcVihicleDaoTest {
    private VehicleConfiguration configuration = new VehicleConfiguration();
    private PlainJdbcVehicleDao vehicleDao;

    @BeforeEach
    public void before(){
        vehicleDao = new PlainJdbcVehicleDao(configuration.simlpeDataSource());
    }

    @Test
    public void testInsertIsSuccessful(){
        Vehicle vehicle =new Vehicle();
        vehicle.setColor("Red");
        vehicle.setSeat(5);
        vehicle.setVehicleNo("12345");
        vehicle.setWheel(4);

        vehicleDao.insert(vehicle);
        List<Vehicle> list = vehicleDao.findAll();
        Assertions.assertTrue(list.size() == 6);
    }
}
