package spring.basic.domain.autowiring.qualifier;

import org.springframework.stereotype.Component;

@Component
public class Mustang implements Car {
    @Override
    public String getCarName() {
        return "This is Mustang";
    }
}
