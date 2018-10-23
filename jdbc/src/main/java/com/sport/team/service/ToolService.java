package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Tool;
import com.sport.team.entity.User;
import com.sport.team.interfaces.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ToolService implements MainService<Tool> {


    /**
     * Inits the db User tables.
     *
     * @throws SQLException the SQL exception
     */
    public static void initDb() throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            conn.setAutoCommit(false);

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Tools");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Tools(id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.executeUpdate();

            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            conn.rollback();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }

    }


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
     */
    @Override
    public void set(Tool tool) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {
            stmt = conn.prepareStatement("UPDATE Community SET name=? WHERE id=?");
            stmt.setString(1, tool.getName());
            stmt.setInt(2, tool.getId());

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
     * Gets the tool.
     *
     * @param id the id
     * @return the tool
     * @throws SQLException the SQL exception
     */
    @Override
    public Tool get(int id) throws SQLException {

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("SELECT Tools.id, Tools.name FROM Tools WHERE Tools.id =?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            rs.next();

            Tool tool = new Tool();
            tool.setId(rs.getInt(1));
            tool.setName(rs.getString(2));

            return tool;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }

    }


    /**
     * Delete tool.
     *
     * @param tool the tool
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(Tool tool) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Tools WHERE id=?");
            stmt.setInt(1, tool.getId());
            stmt.executeUpdate();
            stmt.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }


    /**
     * Delete tool.
     *
     * @param id the tool
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(int id) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Tools  WHERE id=" + id);

            stmt.executeUpdate();
            stmt.close();


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
