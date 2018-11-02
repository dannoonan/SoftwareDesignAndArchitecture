package ie.demo.service.impl;

import org.springframework.stereotype.Service;

import ie.demo.service.PasswordService;

import java.util.Base64;

@Service
public class PasswordServiceImpl implements PasswordService {

    @Override
    public String encryptPassword(String password) {
        String b64encoded = Base64.getEncoder().encodeToString(password.getBytes());

        String reverse = new StringBuffer(b64encoded).reverse().toString();

        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < reverse.length(); i++) {
            tmp.append((char)(reverse.charAt(i) + OFFSET));
        }
        return tmp.toString();
    }

    @Override
    public String decryptPassword(String secret) {
        StringBuilder tmp = new StringBuilder();
        final int OFFSET = 4;
        for (int i = 0; i < secret.length(); i++) {
            tmp.append((char)(secret.charAt(i) - OFFSET));
        }

        String reversed = new StringBuffer(tmp.toString()).reverse().toString();
        return new String(Base64.getDecoder().decode(reversed));
    }
}
