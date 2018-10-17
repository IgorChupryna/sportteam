package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Skill;
import com.sport.team.interfaces.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SkillService implements MainService<Skill> {
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
    public void set(Skill skill) {

    }


    /**
     * Gets the skill.
     *
     * @param id the id
     * @return the skill
     * @throws SQLException the SQL exception
     */
    @Override
    public Skill get(int id) {
        return null;
    }



}
