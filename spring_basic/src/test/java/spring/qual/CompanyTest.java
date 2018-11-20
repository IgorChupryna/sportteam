package spring.qual;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.basic.configuration.AppConfig;
import spring.basic.domain.autowiring.qualifier.Bond;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Builder.class,Analytic.class,Engineer.class,Company.class})
public class CompanyTest {

    @Autowired
    Company company;

    @Test
    public void showPayRate(){


        System.out.println("ff");
        //company.showRate();


    }
}
