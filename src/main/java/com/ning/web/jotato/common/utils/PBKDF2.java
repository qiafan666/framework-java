package com.ning.web.jotato.common.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Random;

@Slf4j
public class PBKDF2 {

    private PBKDF2() {}

    static byte[] deriveKey(String password) {
        try {
            byte[] salt = new byte[8];
            Random ran = new Random();
            ran.nextBytes(salt);

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1024, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey secret = factory.generateSecret(spec);
            byte[] pbkdf2 = secret.getEncoded();

            byte[] all = new byte[salt.length + pbkdf2.length];
            System.arraycopy(salt, 0, all, 0, salt.length);
            System.arraycopy(pbkdf2, 0, all, salt.length, pbkdf2.length);

            return all;
        } catch (Exception e) {
            log.error("deriveKey failed, exception", e);
            return null;
        }
    }

    static boolean verify(String password, byte[] pbkdf2) {
        try {
            byte[] salt = new byte[8];
            byte[] pb = new byte[pbkdf2.length - 8];

            System.arraycopy(pbkdf2, 0, salt, 0, salt.length);
            System.arraycopy(pbkdf2, salt.length, pb, 0, pb.length);

            KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 1024, 128);
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            SecretKey secret = factory.generateSecret(spec);

            return Arrays.equals(secret.getEncoded(), pb);
        } catch (Exception e) {
            log.error("verify failed, exception", e);
            return false;
        }
    }
}
