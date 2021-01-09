package com.idus.homework.util;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

/**
 * 암호화 클래스
 */
public class Crypto {
    private static final String CRYPTO_KEY = "kyhhomework01!!#"; // 128 bit key
    private static final String INIT_VECTOR = "RandomInitVector";    // 16 bytes IV

    /**
     * 암호화
     */
    public static String encrypt(String value) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec sKeySpec = new SecretKeySpec(CRYPTO_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
            byte[] encrypted = cipher.doFinal(value.getBytes());
            return Base64.encodeBase64String(encrypted);
        } catch (Exception ignored) {
        }
        return null;
    }

    /**
     * 복호화
     */
    public static byte[] decrypt(byte[] data) {
        try {
            IvParameterSpec iv = new IvParameterSpec(INIT_VECTOR.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec sKeySpec = new SecretKeySpec(CRYPTO_KEY.getBytes(StandardCharsets.UTF_8), "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, iv);
            return cipher.doFinal(Base64.decodeBase64(new String(data, StandardCharsets.UTF_8)));
        } catch (Exception ignored) {
        }
        return null;
    }
}
