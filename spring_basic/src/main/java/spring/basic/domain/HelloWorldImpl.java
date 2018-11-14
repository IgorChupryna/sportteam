package spring.basic.domain;

import spring.basic.domain.HelloWorld;

public class HelloWorldImpl implements HelloWorld {
    @Override
    public void sayHello(String name) {
        System.out.println("Hello world "+name);
    }
}
