package hibernate;

import crud.SkillService;
import entity.Skill;
import entity.Tool;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class SkillServiceTest {
    SkillService service = new SkillService();

    @Test
    public void testGetAll() throws SQLException {

        List<Skill> skills = service.getAll();
        for(Skill c: skills){
            System.out.println(c);
        }
    }


}
