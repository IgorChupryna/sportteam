package spring.basic.domain.autowiring.autowiredNo.domain;

public class License {
    private String number;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "License{" +
                "number='" + number + '\'' +
                '}';
    }
}
