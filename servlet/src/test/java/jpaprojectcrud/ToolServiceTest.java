package jpaprojectcrud;

import crud.ToolService;
import entity.Tool;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class ToolServiceTest {

    ToolService toolService = new ToolService();

    @Test
    public void testSaveRecord() throws Exception {
        Tool tool1 = new Tool();
        tool1.setName("JavaCore");

        Tool tool2 = toolService.add(tool1);
        System.out.println(tool2);
    }


    @Test
    public void testDeleteRecord() throws Exception {
        Tool tool1 = new Tool();
        tool1.setName("C++");

        Tool tool2 = toolService.add(tool1);

        toolService.del(tool2.getId());
    }

    @Test
    public void testUpdate() throws Exception {
        Tool tool1 = new Tool();
        tool1.setName("Pyton");

        Tool tool2 =  toolService.add(tool1);
        tool2.setName("Pascal");

        toolService.set(tool2);

        Tool tool3 = toolService.get(tool2.getId());
        System.out.println(tool3);
    }

    @Test
    public void testSelect() throws Exception {
        Tool tool1 = new Tool();
        tool1.setName("Pyton");

        Tool tool2 = toolService.add(tool1);
        Tool toolDB = toolService.get(tool2.getId());
        System.out.println(toolDB);
    }

    @Test
    public void testGetAll() throws SQLException {
        Tool tool1 = new Tool();
        tool1.setName("Pyton");

        Tool tool2 = new Tool();
        tool2.setName("C#");

        Tool tool3 = new Tool();
        tool3.setName("Ruby");

        Tool tool4 = new Tool();
        tool4.setName("JavaEE");

        toolService.add(tool1);
        toolService.add(tool2);
        toolService.add(tool3);
        toolService.add(tool4);

        List<Tool> tools = toolService.getAll();
        for(Tool c: tools){
            System.out.println(c);
        }
    }



}
