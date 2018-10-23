package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Community;
import com.sport.team.interfaces.MainService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class CommunityService implements MainService<Community> {


    /**
     * Inits the db Community tables.
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

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Community");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Community(id INT PRIMARY KEY, name VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();


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
     * Insert community.
     *
     * @param community the community
     * @throws SQLException the SQL exception
     */
    @Override
    public void add(Community community) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("INSERT INTO Community VALUES(?, ?)");
            stmt.setInt(1, community.getId());
            stmt.setString(2, community.getName());
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
     * Update community.
     *
     * @param community the community
     * @throws SQLException the SQL exception
     */
    @Override
    public void set(Community community) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("UPDATE Community SET name=? WHERE id=?");
            stmt.setString(1, community.getName());
            stmt.setInt(2, community.getId());
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
     * Gets the community.
     *
     * @param id the id
     * @return the community
     * @throws SQLException the SQL exception
     */
    @Override
    public Community get(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("SELECT Community.id, Community.name FROM Community WHERE Community.id =?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            rs.next();

            Community community = new Community();
            community.setId(rs.getInt(1));
            community.setName(rs.getString(2));

            rs.close();
            stmt.close();

            return community;
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
     * Delete community.
     *
     * @param community the community
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(Community community) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Community  WHERE id=?");
            stmt.setInt(1, community.getId());
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
     * Delete community.
     *
     * @param id the community
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(int id) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Community  WHERE id="+id);
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
