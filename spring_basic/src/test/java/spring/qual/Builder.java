package spring.qual;

import org.springframework.stereotype.Component;

@Component
public class Builder implements Employee {
    @Override
    public String payRate() {
        return "Builder payRate 100";
    }

    public Builder() {
    }
}
