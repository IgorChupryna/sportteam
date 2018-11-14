package spring.basic.domain;

public class ActiveMessaging implements Messaging {
    @Override
    public void sendMessage() {
        System.out.println("Sending Message via ActiveMessaging!");
    }
}
