package spring.basic.domain.autowiring.qualifier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Bond {
    @Autowired
    @Qualifier("mustang")
    private Car car;

    public void showCar(){
        System.out.println(car.getCarName());
    }
}
