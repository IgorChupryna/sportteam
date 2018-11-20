package spring.basic.domain.autowiring.annotations.byType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("programmer")
public class Programmer {
    private Laptop laptop;
    @Autowired
    private  MobilePhone mobilePhone;

    private IdCard idCard;

    @Autowired
    public Programmer(IdCard idCard){
        this.idCard=idCard;
    }

    @Autowired
    public Laptop getLaptop() {
        return laptop;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }

    public MobilePhone getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(MobilePhone mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public IdCard getIdCard() {
        return idCard;
    }

    public void setIdCard(IdCard idCard) {
        this.idCard = idCard;
    }
}
