package spring.basic.domain.autowiring.annotations.byType;

import org.springframework.stereotype.Component;

@Component
public class IdCard {

    private String number = "666896989868968";

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "IdCard{" +
                "number='" + number + '\'' +
                '}';
    }
}
