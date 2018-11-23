package spring.qual;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Builder.class,Analytic.class,Engineer.class,Company.class})
public class CompanyTest {

    @Autowired
    Company company;

    @Test
    public void showPayRate(){
        company.showRate();
    }
}
