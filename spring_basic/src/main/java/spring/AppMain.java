package spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import spring.basic.configuration.AppConfig;
import spring.basic.domain.Communication;
import spring.basic.domain.GoodBye;
import spring.basic.domain.HelloWorld;
import spring.basic.domain.autowiring.byName.domain.Application;
import spring.basic.domain.autowiring.byType.domain.Employee;

public class AppMain {
    public static void main(String[] args){
        AbstractApplicationContext context  =  new AnnotationConfigApplicationContext(AppConfig.class) ;

        HelloWorld bean = (HelloWorld) context.getBean("helloWorldBean");
        bean.sayHello("Spring 4");

        GoodBye goodByeBean = (GoodBye) context.getBean("goodByeBean");
        goodByeBean.sayGoodBye("Good Bye!");

       Communication communication = (Communication) context.getBean("communication");
       communication.communicate();
       communication.encrypt();
       communication.both();

        AbstractApplicationContext contextDependencyInjection = new ClassPathXmlApplicationContext("helloworld-config.xml");
        Communication comm = (Communication) contextDependencyInjection.getBean("communication");
        comm.communicate();
        comm.encrypt();
        comm.both();

        Application application = (Application) contextDependencyInjection.getBean("application");
        System.out.println("Application Details : "+application);

        Employee employee = (Employee) contextDependencyInjection.getBean("employee");
        System.out.println("Employee Details : "+employee);

    }
}
