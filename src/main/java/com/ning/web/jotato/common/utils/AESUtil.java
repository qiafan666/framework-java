package com.ning.web.jotato.common.utils;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Random;

@Slf4j
public class AESUtil {

    private AESUtil() {
    }

    public enum EncryptMode {
        CBC("AES/CBC/PKCS5Padding"),
        ECB("AES/ECB/PKCS5Padding");

        @Getter
        private String mode;

        private EncryptMode(String mode) {
            this.mode = mode;
        }

        public Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
            return Cipher.getInstance(mode);
        }
    }

    /**
     * AES加密解密算法
     */
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 返回一个定长的带因子的固定的随机字符串(只包含大小写字母、数字)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateStringByKey(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return sb.toString();
    }

    public static String encrypt(String key, String text, EncryptMode encryptMode) {
        try {
            byte[] cipher = encrypt(key.getBytes(), text.getBytes(), encryptMode);
            return new String(Base64.getEncoder().encode(cipher));
        } catch (Exception e) {
            log.info("encrypt failed. exception", e);
            return "";
        }
    }

    public static String decrypt(String key, String cipherText, EncryptMode encryptMode) {
        try {
            byte[] cipherBytes = Base64.getDecoder().decode(cipherText);
            byte[] text = decrypt(key.getBytes(), cipherBytes, encryptMode);
            return new String(text);
        } catch (Exception e) {
            log.info("decrypt failed. exception", e);
            return "";
        }
    }

    static byte[] encrypt(byte[] key, byte[] text, EncryptMode encryptMode) {
        try {
            Cipher cipher = encryptMode.getCipher();
            byte[] iv = new byte[cipher.getBlockSize()];

            byte[] aesKeyBytes = new byte[16];
            int len = key.length > 16 ? 16 : key.length;
            System.arraycopy(key, 0, aesKeyBytes, 0, len);
            SecretKeySpec skeySpec = new SecretKeySpec(aesKeyBytes, "AES");

            if (encryptMode == EncryptMode.ECB) {
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
            } else {
                Random ran = new Random();
                ran.nextBytes(iv);
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
            }

            byte[] results = cipher.doFinal(text);

            if (encryptMode == EncryptMode.ECB) {
                return results;
            } else {

                byte[] retBytes = new byte[iv.length + results.length];
                System.arraycopy(iv, 0, retBytes, 0, iv.length);
                System.arraycopy(results, 0, retBytes, iv.length, results.length);
                return retBytes;
            }

        } catch (Exception e) {
            log.error("encrypt failed. exception", e);
            return new byte[0];
        }
    }

    static byte[] decrypt(byte[] key, byte[] cipherBytes, EncryptMode encryptMode) {
        try {
            if (cipherBytes.length < 16 || cipherBytes.length % 16 != 0) {
                return new byte[0];
            }

            Cipher cp = encryptMode.getCipher();
            byte[] iv = new byte[cp.getBlockSize()];

            byte[] aesKeyBytes = new byte[16]; // 16 bytes for AES-256
            int len = key.length > 16 ? 16 : key.length;
            System.arraycopy(key, 0, aesKeyBytes, 0, len);
            SecretKeySpec skeySpec = new SecretKeySpec(aesKeyBytes, "AES");

            if (encryptMode == EncryptMode.ECB) {
                cp.init(Cipher.DECRYPT_MODE, skeySpec);
                return cp.doFinal(cipherBytes);
            } else {

                System.arraycopy(cipherBytes, 0, iv, 0, iv.length);
                IvParameterSpec ivSpec = new IvParameterSpec(iv);
                cp.init(Cipher.DECRYPT_MODE, skeySpec, ivSpec);

                byte[] cip = new byte[cipherBytes.length - iv.length];
                System.arraycopy(cipherBytes, iv.length, cip, 0, cip.length);
                return cp.doFinal(cip);
            }

        } catch (Exception e) {
            log.error("decrypt failed. exception", e);
            return new byte[0];
        }
    }
}
