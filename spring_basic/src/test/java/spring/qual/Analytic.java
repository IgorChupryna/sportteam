package spring.qual;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Analytic implements Employee {
    @Override
    public String payRate() {
        return "Analytic payRate 10000";
    }

    public Analytic() {
    }
}
