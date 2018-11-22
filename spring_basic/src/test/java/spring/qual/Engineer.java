package spring.qual;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class Engineer implements Employee {
    @Override
    public String payRate() {
        return "Engineer payRate 5000";
    }

    public Engineer() {
    }
}
