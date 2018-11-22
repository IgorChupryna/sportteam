package org.spring.jdbc;

import java.util.Collection;
import java.util.List;

public interface VehicleDao {
    void insert(Vehicle vehicle);

    default void insert(Collection<Vehicle> vehicles){
        vehicles.forEach(this::insert);
    }
    void update(Vehicle vehicle);
    void delete(Vehicle vehicle);

    Vehicle findByVehicleNo(String vehicleNo);

    List<Vehicle> findAll();
}
