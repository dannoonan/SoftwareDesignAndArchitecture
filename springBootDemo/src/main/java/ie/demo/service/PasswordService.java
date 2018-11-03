package ie.demo.service;

public interface PasswordService {

    String encryptPassword(String password);
    String decryptPassword(String secret);

}
