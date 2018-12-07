package org.spring.jdbc.service;

import org.spring.jdbc.dao.MainDao;
import org.spring.jdbc.model.Comment;
import org.spring.jdbc.model.Donation;
import org.spring.jdbc.model.Project;
import org.spring.jdbc.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProjectServiceImpl implements MainDao<Project> {
    private static final String INSERT_SQL = "INSERT INTO Project (Project.address1,Project.address2, Project.dateAdded,Project.phone, Project.email, Project.city, Project.description, Project.firstname, Project.lastname, Project.state, Project.title, Project.zip)  VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";

    private static final String INSERT_SQL_ORGANIZER = "INSERT INTO User_projectsOrganized (userId, projectId) VALUES(?, ?)";
    private static final String INSERT_SQL_SUBMITTER = "INSERT INTO User_projectsSubmitted (userId, projectId) VALUES(?, ?)";
    private static final String INSERT_SQL_VOLUNTEERS = "INSERT INTO User_projectsVolunteered (userId, projectId) VALUES(?, ?)";
    private static final String INSERT_SQL_COMMENTS = "INSERT INTO Project_Comments (projectId, commentId) VALUES(?, ?)";
    private static final String INSERT_SQL_DONATION = "INSERT INTO Project_Donations (projectId, donationId) VALUES(?, ?)";


    private static final String UPDATE_SQL = "UPDATE Project  SET Project.address1=?, Project.address2=?, Project.dateAdded=?," +
            "Project.phone=?, Project.email=?, Project.city=?, Project.description=?, Project.firstname=?, Project.lastname=?, Project.state=?, Project.title=?, Project.zip=? WHERE  Project.id=?";

    private static final String SELECT_ALL_SQL = "SELECT * FROM Project";
    private static final String SELECT_ONE_SQL = "SELECT * FROM Project WHERE Project.id=?";

    private static final String DELETE_SQL = "DELETE FROM Project WHERE id=?";

    private static final String DELETE_SQL_ORGANIZER = "DELETE FROM User_projectsOrganized  WHERE User_projectsOrganized.projectId=?";
    private static final String DELETE_SQL_SUBMITTER = "DELETE FROM User_projectsSubmitted  WHERE User_projectsSubmitted.projectId=?";
    private static final String DELETE_SQL_VOLUNTEERS = "DELETE FROM User_projectsVolunteered  WHERE User_projectsVolunteered.projectId=?";
    private static final String DELETE_SQL_COMMENTS = "DELETE FROM Project_Comments  WHERE Project_Comments.projectId=?";
    private static final String DELETE_SQL_DONATION = "DELETE FROM Project_Donations  WHERE Project_Donations.projectId=?";

    private final JdbcTemplate jdbcTemplate;

    public ProjectServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate=jdbcTemplate;
    }


    @Override
    public Project get(int id) {
        return jdbcTemplate.queryForObject(SELECT_ONE_SQL, BeanPropertyRowMapper.newInstance(Project.class), id);
    }

    @Override
    public void add(Project project) {
        jdbcTemplate.update(INSERT_SQL, project.getAddress1(),project.getAddress2(),project.getDateAdded(),project.getPhone(),project.getEmail(),project.getCity(),project.getDescription(),
                project.getFirstName(),project.getLastName(),project.getState(),project.getTitle(),project.getZip());

        jdbcTemplate.update(INSERT_SQL_ORGANIZER, project.getOrganizer().getId(), project.getId());
        jdbcTemplate.update(INSERT_SQL_SUBMITTER, project.getSubmitter().getId(), project.getId());
        for (Comment c:project.getComments()) {
            jdbcTemplate.update(INSERT_SQL_COMMENTS,  project.getId(),c.getId());
        }
        for (Donation d:project.getDonations()) {
            jdbcTemplate.update(INSERT_SQL_DONATION,  project.getId(),d.getId());
        }

        for (Comment c:project.getComments()) {
            jdbcTemplate.update(INSERT_SQL_VOLUNTEERS, project.getOrganizer().getId(), project.getId());
        }
    }

    @Override
    public void add(Collection<Project> projects) {
        jdbcTemplate.batchUpdate(INSERT_SQL, projects, projects.size(), this::prepareStatement);
        jdbcTemplate.batchUpdate(INSERT_SQL_ORGANIZER, projects, projects.size(), this::prepareStatementOrg);
        jdbcTemplate.batchUpdate(INSERT_SQL_SUBMITTER, projects, projects.size(), this::prepareStatementSub);

        for (Project project:projects) {
            for (User u:project.getVolunteers()) {
                jdbcTemplate.update(INSERT_SQL_VOLUNTEERS, u.getId(), project.getId());
            }
            for (Comment c:project.getComments()) {
                jdbcTemplate.update(INSERT_SQL_COMMENTS, c.getId(), project.getId());
            }
            for (Donation d:project.getDonations()) {
                jdbcTemplate.update(INSERT_SQL_DONATION, d.getId(), project.getId());
            }
        }

    }

    @Override
    public void set(Project project) {
        jdbcTemplate.update(UPDATE_SQL, ps -> prepareStatementUpdate(ps, project));

        jdbcTemplate.update(DELETE_SQL_COMMENTS,project.getId());
        jdbcTemplate.update(DELETE_SQL_DONATION,project.getId());
        jdbcTemplate.update(DELETE_SQL_ORGANIZER,project.getId());
        jdbcTemplate.update(DELETE_SQL_SUBMITTER,project.getId());
        jdbcTemplate.update(DELETE_SQL_VOLUNTEERS,project.getId());

        jdbcTemplate.update(INSERT_SQL_ORGANIZER, project.getOrganizer().getId(), project.getId());
        jdbcTemplate.update(INSERT_SQL_SUBMITTER, project.getSubmitter().getId(), project.getId());
        for (Comment c:project.getComments()) {
            jdbcTemplate.update(INSERT_SQL_COMMENTS,  project.getId(),c.getId());
        }
        for (Donation d:project.getDonations()) {
            jdbcTemplate.update(INSERT_SQL_DONATION,  project.getId(),d.getId());
        }

        for (Comment c:project.getComments()) {
            jdbcTemplate.update(INSERT_SQL_VOLUNTEERS, project.getOrganizer().getId(), project.getId());
        }

    }

    @Override
    public void del(Project project) {
        jdbcTemplate.update(DELETE_SQL,project.getId());
        jdbcTemplate.update(DELETE_SQL_COMMENTS,project.getId());
        jdbcTemplate.update(DELETE_SQL_DONATION,project.getId());
        jdbcTemplate.update(DELETE_SQL_ORGANIZER,project.getId());
        jdbcTemplate.update(DELETE_SQL_SUBMITTER,project.getId());
        jdbcTemplate.update(DELETE_SQL_VOLUNTEERS,project.getId());
    }

    @Override
    public void del(int id) {
        jdbcTemplate.update(DELETE_SQL,id);
        jdbcTemplate.update(DELETE_SQL_COMMENTS,id);
        jdbcTemplate.update(DELETE_SQL_DONATION,id);
        jdbcTemplate.update(DELETE_SQL_ORGANIZER,id);
        jdbcTemplate.update(DELETE_SQL_SUBMITTER,id);
        jdbcTemplate.update(DELETE_SQL_VOLUNTEERS,id);
    }


    @Override
    public List<Project> findAll() {
        List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_ALL_SQL);
        return rows.stream().map(row -> {
            Project project = new Project();
            project.setId((Integer)row.get("id"));
            project.setAddress1((String) row.get("address1"));
            project.setAddress2((String) row.get("address2"));
            project.setCity((String) row.get("city"));
            project.setDateAdded((Calendar) row.get("dateAdded"));
            project.setDescription((String) row.get("description"));
            project.setEmail((String) row.get("email"));
            project.setFirstName((String) row.get("firstname"));
            project.setLastName((String) row.get("lastname"));
            project.setPhone((String) row.get("phone"));
            project.setState((String) row.get("state"));
            project.setZip((String) row.get("zip"));
            project.setTitle((String) row.get("title"));
            return project;
        }).collect(Collectors.toList());
    }

    private void prepareStatement(PreparedStatement ps, Project project) throws SQLException {
        ps.setString(1,project.getAddress1());
        ps.setString(2,project.getAddress2());
        ps.setDate(3,new java.sql.Date(project.getDateAdded().getTimeInMillis()));
        ps.setString(4,project.getPhone());
        ps.setString(5,project.getEmail());
        ps.setString(6,project.getCity());
        ps.setString(7,project.getDescription());
        ps.setString(8,project.getFirstName());
        ps.setString(9,project.getLastName());
        ps.setString(10,project.getState());
        ps.setString(11,project.getTitle());
        ps.setString(12,project.getZip());
    }

    private void prepareStatementUpdate(PreparedStatement ps, Project project) throws SQLException {
        ps.setString(1,project.getAddress1());
        ps.setString(2,project.getAddress2());
        ps.setDate(3,new java.sql.Date(project.getDateAdded().getTimeInMillis()));
        ps.setString(4,project.getPhone());
        ps.setString(5,project.getEmail());
        ps.setString(6,project.getCity());
        ps.setString(7,project.getDescription());
        ps.setString(8,project.getFirstName());
        ps.setString(9,project.getLastName());
        ps.setString(10,project.getState());
        ps.setString(11,project.getTitle());
        ps.setString(12,project.getZip());
        ps.setInt(13,project.getId());
    }


    private void prepareStatementOrg(PreparedStatement ps, Project project) throws SQLException {
        ps.setInt(1, project.getOrganizer().getId());
        ps.setInt(2, project.getId());
    }
    private void prepareStatementSub(PreparedStatement ps, Project project) throws SQLException {
        ps.setInt(1, project.getSubmitter().getId());
        ps.setInt(2, project.getId());
    }



}
