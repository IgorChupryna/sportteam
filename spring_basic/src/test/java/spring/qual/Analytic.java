package spring.qual;

import org.springframework.stereotype.Component;

@Component
public class Analytic implements Employee {
    @Override
    public String payRate() {
        return "Analytic payRate 10000";
    }

    public Analytic() {
    }
}
