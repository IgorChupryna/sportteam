package hibernate;

import crud.SkillService;
import entity.Skill;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class SkillServiceTest {

    SkillService skillService = new SkillService();

    @Test
    public void getAll() throws SQLException {
        List<Skill> skills = skillService.getAll();
        for (Skill s: skills){
            System.out.println(s);
        }
    }
}
