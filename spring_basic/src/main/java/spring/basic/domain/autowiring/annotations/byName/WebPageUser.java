package spring.basic.domain.autowiring.annotations.byName;

import org.springframework.stereotype.Component;



@Component("webPageUser")
public class WebPageUser {
    private String name = "defaultName";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "WebPageUser{" +
                "name='" + name + '\'' +
                '}';
    }
}
