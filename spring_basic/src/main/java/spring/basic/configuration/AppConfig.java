package spring.basic.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import spring.basic.domain.*;

@Configuration
@ComponentScan("spring")
public class AppConfig {
    @Bean(name = "helloWorldBean")
    @Description("This is a sample")
    public HelloWorld helloWorld() {
        return new HelloWorldImpl();
    }

    @Bean(name = "goodByeBean")
    public GoodBye goodBye() {
        return new GoodByeImpl();
    }
    @Bean(name = "messaging")
    @Description("This bean will be injected via setter injection")
    public Messaging messaging() {
        return new ActiveMessaging();
    }

    @Bean(name = "encryption")
    public Encryption encryption() {
        return new RSAEncryption();
    }


    @Bean(name = "communication")
    public Communication communication(){
        Communication communication = new Communication(encryption());
        communication.setMessaging(messaging());
        return communication;
    }




}
