package spring.basic.domain;

public class Communication {

    private Messaging messaging;

    private Encryption encryption;

    public Communication(Encryption encryption) {
        this.encryption = encryption;
    }

    public void setMessaging(Messaging messaging) {
        this.messaging = messaging;
    }

    public void communicate() {
        messaging.sendMessage();
    }

    public void encrypt() {
        encryption.encryptData();
    }

    public void both() {
        messaging.sendMessage();
        encryption.encryptData();
    }
}