package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Comment;
import com.sport.team.entity.Project;
import com.sport.team.entity.User;
import com.sport.team.interfaces.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentService implements MainService<Comment> {

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


            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Comment");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Comment(id INT PRIMARY KEY, dateAdded DATE, text VARCHAR(255))");
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
     * Insert comment.
     *
     * @param comment the user
     * @throws SQLException the SQL exception
     */
    @Override
    public void add(Comment comment) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("INSERT INTO Comment VALUES(?, ?, ?)");
            stmt.setInt(1, comment.getId());
            stmt.setDate(2, new java.sql.Date(comment.getDateAdded().getTimeInMillis()));
            stmt.setString(3, comment.getText());

            stmt.executeUpdate();
            //stmt.close();

            addTables(comment);
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
     * @param comment the comment
     * @throws SQLException the SQL exception
     */
    private void addTables(Comment comment) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("INSERT INTO Users_Comments VALUES(?, ?)");
            stmt.setInt(1, comment.getSubmitter().getId());
            stmt.setInt(2, comment.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("INSERT INTO Project_Comments VALUES(?, ?)");
            stmt.setInt(1, comment.getProject().getId());
            stmt.setInt(2, comment.getId());
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
     * @param comment the comment
     * @throws SQLException the SQL exception
     */
    private void delTables(Comment comment) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();

            stmt = conn.prepareStatement("DELETE FROM Users_Comments  WHERE Users_Comments.commentId=" + comment.getId());
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DELETE FROM Project_Comments  WHERE Project_Comments.commentId=" + comment.getId());
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
     * Update comment.
     *
     * @param comment the user
     * @throws SQLException the SQL exception
     */
    @Override
    public void set(Comment comment) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = MainJdbc.connection();
            stmt = conn.prepareStatement("UPDATE Comment SET Comment.dateAdded=?,Comment.text=? WHERE Comment.id=?");
            stmt.setDate(1, new java.sql.Date(comment.getDateAdded().getTimeInMillis()));
            stmt.setString(3, comment.getText());
            stmt.setInt(3, comment.getId());

            stmt.executeUpdate();
            stmt.close();

            delTables(comment);
            addTables(comment);
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
     * Gets the comment.
     *
     * @param id the id
     * @return the comment
     * @throws SQLException the SQL exception
     */
    @Override
    public Comment get(int id) throws SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("SELECT Comment.id, Comment.dateAdded,Comment.text FROM Comment WHERE Comment.id =?");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            rs.next();

            Comment comment = new Comment();
            comment.setId(rs.getInt(1));
            comment.setDateAdded(DonationService.toCalendar(rs.getDate(2)));
            comment.setText(rs.getString(3));

            rs.close();
            stmt.close();


            stmt = conn.prepareStatement("SELECT Users.id, FROM Users, Users_Comments "
                    + "WHERE Users_Comments.commentId=? AND Users_Comments.userId=Users.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            User submitter = new UserService().get(rs.getInt(1));
            comment.setSubmitter(submitter);

            rs.close();
            stmt.close();

            stmt = conn.prepareStatement("SELECT Project.id, FROM Project, Project_Comments "
                    + "WHERE Project_Comments.commentId=? AND Project_Comments.projectId=Project.id");
            stmt.setInt(1, id);
            rs = stmt.executeQuery();

            Project project = new ProjectService().get(rs.getInt(1));
            comment.setProject(project);

            return comment;
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
     * Delete comment.
     *
     * @param comment the comment
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(Comment comment) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Comment  WHERE Comment.id=" + comment.getId());
            stmt.executeUpdate();
            stmt.close();

            delTables(comment);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    /**
     * Delete comment.
     *
     * @param id the comment
     * @throws SQLException the SQL exception
     */
    @Override
    public void del(int id) throws SQLException {
        PreparedStatement stmt = null;

        try (Connection conn = MainJdbc.connection()) {

            stmt = conn.prepareStatement("DELETE FROM Comment  WHERE Comment.id=" + id);
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
