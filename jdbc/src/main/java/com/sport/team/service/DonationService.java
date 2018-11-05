package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Donation;
import com.sport.team.entity.Project;
import com.sport.team.entity.User;
import com.sport.team.interfaces.MainService;

import java.sql.*;
import java.util.Calendar;

public class DonationService implements MainService<Donation> {

    /**
     * Inits the db Donation tables.
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

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Donation");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Donation(id INT PRIMARY KEY, amount BIGINT(64), dateAdded DATE )");
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
     * Insert donation.
     *
     * @param donation the donation
     * @throws SQLException the SQL exception
     */
    @Override
    public void add(Donation donation) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("INSERT INTO Donation VALUES(?, ?, ?)");
            stmt.setInt(1, donation.getId());
            stmt.setDouble(2, donation.getAmount());
            stmt.setDate(3, donation.getDateAdded());
            stmt.executeUpdate();

            // / stmt.close();

            addTables(donation);
        } catch (Exception e) {
            e.printStackTrace();

            if (conn != null)
                conn.rollback();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Add additional tables.
     *
     * @param donation the donation
     * @throws SQLException the SQL exception
     */
    private void addTables(Donation donation) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("INSERT INTO Project_Donations VALUES(?, ?)");
            stmt.setInt(1,donation.getProject().getId());
            stmt.setInt(2, donation.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("INSERT INTO User_Donation VALUES(?, ?)");
            stmt.setInt(1, donation.getUser().getId());
            stmt.setInt(2, donation.getId());
            stmt.executeUpdate();

            //stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

            if (conn != null)
                conn.rollback();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Delete additional tables.
     *
     * @param donation the donation
     * @throws SQLException the SQL exception
     */
    private void delTables(Donation donation) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("DELETE FROM Project_Donations  WHERE Project_Donations.donationId=" + donation.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM User_Donation  WHERE User_Donation.donationId=" + donation.getId());
            stmt.executeUpdate();
            //stmt.close();

        } catch (Exception e) {
            e.printStackTrace();

            if (conn != null)
                conn.rollback();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Update donation.
     *
     * @param donation the donation
     * @throws SQLException the SQL exception
     */
    @Override
    public void set(Donation donation) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();
            stmt = conn.prepareStatement("UPDATE Donation SET Donation.amount=?,Donation.dateAdded=? WHERE Donation.id=?");
            stmt.setDouble(1, donation.getAmount());
            stmt.setDate(2, donation.getDateAdded());
            stmt.setInt(3, donation.getId());

            stmt.executeUpdate();
            stmt.close();

            delTables(donation);
            addTables(donation);
        } catch (Exception e) {
            e.printStackTrace();

            if (conn != null)
                conn.rollback();

        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }


    }

    /**
     * Get the cal.
     *
     * @param date the Date
     * @return the Calendar
     */
    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    /**
     * Gets the donation.
     *
     * @param id the id
     * @return the donation
     * @throws SQLException the SQL exception
     */
    @Override
    public Donation get(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("SELECT Donation.id, Donation.amount, Donation.dateAdded FROM Donation WHERE Donation.id =?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            rs.next();

            Donation donation = new Donation();
            donation.setId(rs.getInt(1));
            donation.setAmount(rs.getDouble(2));
            donation.setDateAdded(rs.getDate(3));

            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Project.id FROM Project, Project_Donations WHERE Project_Donations.donationId=? AND Project_Donations.projectId=Project.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();

            Project project = new ProjectService().get(rs.getInt(1));
            donation.setProject(project);

            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Users.id  FROM Users, User_Donation  WHERE User_Donation.donationId=? AND User_Donation.userId=Users.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            rs.next();

            User user  = new UserService().get(rs.getInt(1));
            donation.setUser(user);


            return donation;
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
     * Delete donation.
     *
     * @param donation the donation
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(Donation donation) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Donation  WHERE Donation.id=" + donation.getId());
            stmt.executeUpdate();
            stmt.close();

            delTables(donation);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }

    }

    /**
     * Delete donation.
     *
     * @param id the donation
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(int id) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Donation  WHERE Donation.id=" + id);
            stmt.executeUpdate();
            stmt.close();

            delTables(get(id));

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
