package com.example.badlab.security;

import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class WeakCryptoService {
    private static final String STATIC_KEY = "12345678";

    public String md5PasswordHash(String password) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return Base64.getEncoder().encodeToString(md.digest(password.getBytes()));
    }

    public String encryptWithDes(String value) throws Exception {
        SecretKeySpec key = new SecretKeySpec(STATIC_KEY.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(value.getBytes()));
    }

    public boolean comparePasswords(String providedPassword, String storedPassword) {
        return providedPassword.equals(storedPassword);
    }
}
