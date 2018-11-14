package spring.basic.domain;

public class GoodByeImpl implements  GoodBye {
    @Override
    public void sayGoodBye(String name) {
        System.out.println("Good bye "+name);
    }
}
