package org.spring.jdbc;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SuppressWarnings("all")
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VehicleConfiguration.class})
@Sql(statements = {"CREATE TABLE VEHICLE(VEHICLE_ID SERIAL PRIMARY KEY, VEHICLE_NO varchar(255), COLOR varchar(255), WHEEL INTEGER, SEAT INTEGER);"}
,executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(statements = {"DROP TABLE VEHICLE;"},executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class JdbcVehicleDaoImplTest {
    @Autowired
    private JdbcVehicalDaoImpl jdbcVehicalDao;

    @Test
    public void testInsert(){
        Vehicle vehicle = new Vehicle();
        vehicle.setColor("Red");
        vehicle.setSeat(5);
        vehicle.setVehicleNo("12345");
        vehicle.setWheel(4);
        jdbcVehicalDao.insert(vehicle);
        int all = jdbcVehicalDao.countAll();
        System.out.println(all);

    }
}
