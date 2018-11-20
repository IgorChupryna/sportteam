package spring.basic.domain.autowiring.annotations.byType;

import org.springframework.stereotype.Component;

@Component
public class MobilePhone {
    private String number = "11111111";

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "MobilePhone{" +
                "number='" + number + '\'' +
                '}';
    }
}
