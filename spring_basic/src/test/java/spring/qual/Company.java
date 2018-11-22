package spring.qual;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Company {

    @Autowired
    @Qualifier("engineer")
    private Employee engineer;

    @Autowired
    @Qualifier("analytic")
    private Employee analytic;
    @Autowired
    @Qualifier("builder")
    private Employee builder;

    public void showRate(){
        System.out.println(analytic.payRate());
        System.out.println(engineer.payRate());
        System.out.println(builder.payRate());
    }

    public Company() {
    }
}
