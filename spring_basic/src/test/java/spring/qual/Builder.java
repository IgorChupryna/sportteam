package spring.qual;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class Builder implements Employee {
    @Override
    public String payRate() {
        return "Builder payRate 100";
    }

    public Builder() {
    }
}
