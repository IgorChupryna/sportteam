package com.sport.team.service;

import com.sport.team.MainJdbc;
import com.sport.team.entity.Comment;
import com.sport.team.interfaces.MainService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentService  implements MainService<Comment> {


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

            stmt = conn.prepareStatement("CREATE TABLE Comment(id INT PRIMARY KEY, dateAdded VARCHAR(255), text VARCHAR(255))");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("DROP TABLE IF EXISTS Comment_project");
            stmt.executeUpdate();
            stmt.close();

            stmt = conn.prepareStatement("CREATE TABLE Comment_project(commentId INT, projectId INT, "
                    + "PRIMARY KEY(commentId, projectId))");
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

    }

    /**
     * Update comment.
     *
     * @param comment the user
     * @throws SQLException the SQL exception
     */
    @Override
    public void set(Comment comment) throws SQLException {

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
        return null;
    }

    @Override
    public void del(Comment comment) throws SQLException {

    }

    @Override
    public void del(int id) throws SQLException {

    }
}
