package com.example.demo.util;

import com.example.demo.common.DataValidationException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
public class AesUtil {
    private AesUtil(){throw  new IllegalStateException("AesUtil");}
    public static  String parseByte2HexStr(byte[] buf){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<buf.length;i++){
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if(hex.length() == 1){
                hex = '0'+hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }
    public static byte[] parseHexStr2Byte(String heStr){
        if(heStr.length()<1)
            return new byte[0];
        byte[] result = new byte[heStr.length() / 2];
        for(int i = 0;i < heStr.length() / 2;i++){
            int high = Integer.parseInt(heStr.substring(i*2,i*2+1),16);
            int low = Integer.parseInt(heStr.substring(i*2+1,i*2+2),16);
            result[i] = (byte)(high*16+low);
        }
        return result;
    }
    public static  byte[] decrypt(byte[] content,String cryptKey){
        try{
            SecretKey secretKey = getKey(cryptKey);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat,"AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE,key);
            return cipher.doFinal(content);
        }catch (NoSuchAlgorithmException|NoSuchPaddingException|InvalidKeyException|IllegalBlockSizeException|BadPaddingException e){

        }
        return new byte[0];
    }
    public static byte[] encrypt(String content, String cryptKey) {
        try {
            SecretKey secretKey = getKey(cryptKey);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("UTF-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(byteContent);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e) {
            e.getMessage();
        }
        return new byte[0];
    }

    private static SecretKey getKey(String strKey) {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(strKey.getBytes());
            generator.init(128, secureRandom);
            return generator.generateKey();
        } catch (Exception e) {
            throw new DataValidationException("初始化密钥出现异常");
        }
    }
}
