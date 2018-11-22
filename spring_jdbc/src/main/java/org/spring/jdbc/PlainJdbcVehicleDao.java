package org.spring.jdbc;

import java.sql.PreparedStatement;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PlainJdbcVehicleDao implements VehicleDao {

    private static final String INSERT_SQL = "INSERT INTO Vehicle (color, wheel, seat, vehicle_no) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE Vehicle SET color=?,wheel=?,seat=? WHERE vehicle_no=?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM Vehicle";
    private static final String SELECT_ONE_SQL = "SELECT * FROM Vehicle WHERE vehicle_no = ?";
    private static final String DELETE_SQL = "DELETE FROM Vehicle WHERE vehicle_no=?";

    private final DataSource dataSource;

    public PlainJdbcVehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    private Vehicle toVehecle(ResultSet rs) throws SQLException {
        return new Vehicle(rs.getString("vehicle_no"), rs.getString("color"), rs.getInt("wheel"), rs.getInt("seat"));
    }

    private void prepareStatement(PreparedStatement ps, Vehicle vehicle) throws SQLException {
        ps.setString(1, vehicle.getColor());
        ps.setInt(2, vehicle.getWheel());
        ps.setInt(3, vehicle.getSeat());
        ps.setString(4, vehicle.getVehicleNo());
    }

    @Override
    public void insert(Vehicle vehicle) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_SQL);

        ) {
            prepareStatement(ps, vehicle);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Vehicle vehicle) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_SQL);
        ) {
            prepareStatement(ps, vehicle);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Vehicle vehicle) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_SQL);
        ) {
            ps.setString(1,vehicle.getVehicleNo());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Vehicle findByVehicleNo(String vehicleNo) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ONE_SQL);
        ) {
            ps.setString(1, vehicleNo);
            Vehicle vehicle = null;
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    vehicle = toVehecle(rs);
                }
            }
            return vehicle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> findAll() {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = ps.executeQuery()
        ) {
            List<Vehicle> vehicles = new ArrayList<>();
            while(rs.next()){
                vehicles.add(toVehecle(rs));
            }

            return vehicles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}