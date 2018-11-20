package spring.basic.domain.autowiring.annotations.byType;

import org.springframework.stereotype.Component;

@Component
public class Laptop {
    private String number = "66688968";

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "number='" + number + '\'' +
                '}';
    }
}
