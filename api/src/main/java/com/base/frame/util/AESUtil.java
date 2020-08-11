package com.base.frame.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;

/**
 *  AES加密工具类
 *
 * **/
public class AESUtil {

    /**
     * 加密
     *
     * <p>
     *     根据指定返回的加密内容格式进行加密
     * </p>
     *
     * @param type: 0 = 16进制字符串, 1 = base64字符串
     * */
    public static String encrypt(String data, String key, int type){
        return type == 0 ? encryptIntoHexString(data, key) : encryptIntoBase64String(data, key);
    }

    /**
     * 解密
     *
     * <p>
     *     根据指定返回的加密内容格式进行解密
     * </p>
     *
     * @param type: 0 = 16进制字符串, 1 = base64字符串
     * */
    public static String decrypt(String data, String key, int type){
        return type == 0 ? decryptByHexString(data, key) : decryptByBase64String(data, key);
    }

    /**
     *  加密成十六进制字符串
     *
     *  <p>
     *     使用AES加密，并将Cipher加密后的byte数组转换成16进制字符串
     *  </p>
     *
     * */
    private static String encryptIntoHexString(String data, String key){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec("0000000000000000".getBytes()));
            return bytesConvertHexString(cipher.doFinal(Arrays.copyOf(data.getBytes(), 16 * ((data.getBytes().length / 16) + 1))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将加密后的十六进制字符串进行解密
     *
     * **/
    private static String decryptByHexString(String data, String key){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec("0000000000000000".getBytes()));
            return new String(cipher.doFinal(hexStringConvertBytes(data.toLowerCase())),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  加密成Base64字符串
     *
     * */
    private static String encryptIntoBase64String(String data, String key){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec("0000000000000000".getBytes()));
            return new BASE64Encoder().encode(cipher.doFinal(Arrays.copyOf(data.getBytes(), 16 * ((data.getBytes().length / 16) + 1)))).replaceAll("\r\n", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将加密后的Base64字符串进行解密
     *
     * **/
    private static String decryptByBase64String(String data, String key){
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec("0000000000000000".getBytes()));
            return new String(cipher.doFinal(new BASE64Decoder().decodeBuffer(data)),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *  byte数组转换成十六进制字符串
     *
     * */
    private static String bytesConvertHexString(byte [] data){
        StringBuffer result = new StringBuffer();
        String hexString = "";
        for (byte b : data) {
            hexString = Integer.toHexString(b & 255);
            result.append(hexString.length() == 1 ? "0" + hexString : hexString);
        }
        return result.toString().toUpperCase();
    }

    /**
     * 十六进制字符串转换成byte数组
     *
     * */
    private static byte [] hexStringConvertBytes(String data){
        int length = data.length() / 2;
        byte [] result = new byte[length];
        for (int i = 0; i < length; i++) {
            int first = Integer.parseInt(data.substring(i * 2, i * 2 + 1), 16);
            int second = Integer.parseInt(data.substring(i * 2 + 1, i * 2 + 2), 16);
            result[i] = (byte) (first * 16 + second);
        }
        return result;
    }

}
