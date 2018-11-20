package spring.basic.domain.autowiring.qualifier;

import org.springframework.stereotype.Component;

@Component
public class Ferrari implements Car {
    @Override
    public String getCarName() {
        return "This is Ferrari";
    }
}
