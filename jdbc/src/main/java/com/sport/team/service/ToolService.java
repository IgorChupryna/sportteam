package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;
import com.sport.team.interfaces.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ToolService implements MainService<Tool> {

    /**
     * Insert tool.
     *
     * @param tool the tool
     * @throws SQLException the SQL exception
     */
    @Override
    public void add(Tool tool) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("INSERT INTO Tools VALUES(?, ?)");
            stmt.setInt(1, tool.getId());
            stmt.setString(2, tool.getName());
            stmt.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }


    /**
     * Update tool.
     *
     * @param tool the tool
     * @throws SQLException the SQL exception
     *
     * 
     */
    @Override
    public void set(Tool tool) {

    }


    /**
     * Gets the tool.
     *
     * @param id the id
     * @return the tool
     * @throws SQLException the SQL exception
     */
    @Override
    public Tool get(int id) {
        return null;
    }
}
