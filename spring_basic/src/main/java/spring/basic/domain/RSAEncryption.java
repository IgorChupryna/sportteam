package spring.basic.domain;

public class RSAEncryption implements Encryption {
    @Override
    public void encryptData() {
        System.out.println("Encryption data using RSA Encription!");
    }
}
