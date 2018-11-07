package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Skill;
import com.sport.team.interfaces.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SkillService implements MainService<Skill> {


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

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Skills");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Skills(id INT PRIMARY KEY, name VARCHAR(255))");
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
     * Insert skill.
     *
     * @param skill the skill
     * @throws SQLException the SQL exception
     */
    @Override
    public void add(Skill skill) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("INSERT INTO Skills VALUES(?, ?)");
            stmt.setInt(1, skill.getId());
            stmt.setString(2, skill.getName());
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
     * Update skill.
     *
     * @param skill the skill
     * @throws SQLException the SQL exception
     */
    @Override
    public void set(Skill skill) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {
            stmt = conn.prepareStatement("UPDATE Skills SET name=? WHERE id=?");
            stmt.setString(1, skill.getName());
            stmt.setInt(2, skill.getId());

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
     * Gets the skill.
     *
     * @param id the id
     * @return the skill
     * @throws SQLException the SQL exception
     */
    @Override
    public Skill get(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("SELECT Skills.id, Skills.name FROM Skills WHERE Skills.id =?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            rs.next();

            Skill skill = new Skill();
            skill.setId(rs.getInt(1));
            skill.setName(rs.getString(2));

            rs.close();
            stmt.close();

            return skill;
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
            * Delete skill.
            *
            * @param skill the skill
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(Skill skill) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Skills WHERE id=?");
            stmt.setInt(1, skill.getId());
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
     * Delete skill.
     *
     * @param id the skill
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(int id) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Skills  WHERE id=" + id);

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
