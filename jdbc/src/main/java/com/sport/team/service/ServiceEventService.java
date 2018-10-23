package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Project;
import com.sport.team.entity.ServiceEvent;
import com.sport.team.interfaces.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ServiceEventService implements MainService<ServiceEvent> {


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


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Service_EO");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Service_EO(id INT PRIMARY KEY, name VARCHAR(255),description VARCHAR(255), date DATE )");
            stmt.executeUpdate();
            stmt.close();


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Service_EO_community");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Service_EO_community(serviceEOId INT, communityId INT, "
                    + "PRIMARY KEY(serviceEOId, communityId))");
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
     * Insert serviceEvent.
     *
     * @param serviceEvent the serviceEvent
     * @throws SQLException the SQL exception
     */
    @Override
    public void add(ServiceEvent serviceEvent) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("INSERT INTO Service_EO VALUES(?,?,?,?)");
            stmt.setInt(1, serviceEvent.getId());
            stmt.setString(2, serviceEvent.getName());
            stmt.setString(3, serviceEvent.getDescription());
            stmt.setDate(4,  new java.sql.Date(serviceEvent.getDate().getTimeInMillis()));

            stmt.executeUpdate();
            stmt.close();


            addTables(serviceEvent);
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
     * Update serviceEvent.
     *
     * @param serviceEvent the serviceEvent
     * @throws SQLException the SQL exception
     */
    @Override
    public void set(ServiceEvent serviceEvent) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();
            stmt = conn.prepareStatement("UPDATE Service_EO SET Service_EO.name=?,Service_EO.description=?,Service_EO.date=? WHERE  Service_EO.id=?");
            stmt.setString(1, serviceEvent.getName());
            stmt.setString(2, serviceEvent.getDescription());
            stmt.setDate(3,  new java.sql.Date(serviceEvent.getDate().getTimeInMillis()));
            stmt.setInt(4, serviceEvent.getId());


            stmt.executeUpdate();
            stmt.close();

            delTables(serviceEvent);
            addTables(serviceEvent);
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
     * Gets the serviceEvent.
     *
     * @param id the id
     * @return the serviceEvent
     * @throws SQLException the SQL exception
     */
    @Override
    public ServiceEvent get(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("SELECT Service_EO.id, Service_EO.name,Service_EO.description,Service_EO.date FROM Service_EO WHERE Service_EO.id =?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            rs.next();

            ServiceEvent serviceEvent  = new ServiceEvent();

            serviceEvent.setId(rs.getInt(1));
            serviceEvent.setName(rs.getString(2));
            serviceEvent.setDescription(rs.getString(3));
            serviceEvent.setDate(DonationService.toCalendar(rs.getDate(4)));

            rs.close();
            stmt.close();

            serviceEvent.setProjects(new ArrayList<Project>());


            stmt = conn.prepareStatement("SELECT Project.id, Project.address1, Project.address2, Project.dateAdded," +
                    "Project.phone, Project.email, Project.city, Project.description, Project.firstname, Project.lastname, Project.state, Project.title, Project.zip" +
                    " FROM Project, Project_serviceEvent "
                    + "WHERE Project_serviceEvent.serviceEventId=? AND Project_serviceEvent.projectId=Project.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();


            while (rs.next()) {

                Project project = new ProjectService().get(rs.getInt(1));


                serviceEvent.getProjects().add(project);
            }
            rs.close();
            stmt.close();









            return serviceEvent;
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
     * Delete serviceEvent.
     *
     * @param serviceEvent the serviceEvent
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(ServiceEvent serviceEvent) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Service_EO  WHERE Service_EO.id=" + serviceEvent.getId());
            stmt.executeUpdate();
            stmt.close();

            delTables(serviceEvent);


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Delete serviceEvent.
     *
     * @param id the serviceEvent
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(int id) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Service_EO  WHERE Service_EO.id=" + id);
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

    /**
     * Add additional tables.
     *
     * @param serviceEvent the serviceEvent
     * @throws SQLException the SQL exception
     */
    private void addTables(ServiceEvent serviceEvent) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("INSERT INTO Project_Donations VALUES(?, ?)");
            stmt.setInt(1, serviceEvent.getId());
            //stmt.setInt(2, serviceEvent.getProject().getId());
            stmt.executeUpdate();
            stmt.close();


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
     * @param serviceEvent the serviceEvent
     * @throws SQLException the SQL exception
     */
    private void delTables(ServiceEvent serviceEvent) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("DELETE FROM Project_Donations  WHERE Project_Donations.serviceEventId=" + serviceEvent.getId());
            stmt.executeUpdate();
            stmt.close();


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

}
