/**
 * Copyright 2015 Software innovation and R & D center. All rights reserved.
 * File Name: DesUtils.java
 * Encoding UTF-8
 * Version: 0.0.1
 * History:	2016年10月31日
 */
package cn.chenxins.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/** 
 * 3des加密解密（base64）
 * @author: qigui.su
 * @version Revision: 0.0.1
 * @Date: 2016年10月31日
 */
public class DesUtils {
	 // 向量  
    private final static String iv = "01234567" ;  
    // 加解密统一使用的编码方式  
    private final static String encoding = "utf-8" ;

       
    /** 
     * 3DES加密 
     * @author: qigui.su
     * @param plainText 内容
     * @param key 密匙不小于24
     * @return
     * @throws Exception
     */
    public static String encode(String plainText,String key) throws Exception {  
        Key deskey = null ;  
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());  
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "DESede" );  
        deskey = keyfactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance( "DESede/CBC/PKCS5Padding" );  
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
        cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);  
        byte [] encryptData = cipher.doFinal(plainText.getBytes(encoding));  
        return Base64.getEncoder().encodeToString(encryptData);
    }  
    
    /** 
     * 3des解密
     * @author: qigui.su
     * @param encryptText 内容
     * @param key 密匙不小于24位
     * @return
     * @throws Exception
     */
    public static String decode(String encryptText, String key) throws Exception {  
        Key deskey = null ;  
        DESedeKeySpec spec = new DESedeKeySpec(key.getBytes());   
        SecretKeyFactory keyfactory = SecretKeyFactory.getInstance( "DESede" );  
        deskey = keyfactory.generateSecret(spec);  
        Cipher cipher = Cipher.getInstance( "DESede/CBC/PKCS5Padding" );  
        IvParameterSpec ips = new IvParameterSpec(iv.getBytes());  
        cipher.init(Cipher.DECRYPT_MODE, deskey, ips);  
       
        byte [] decryptData = cipher.doFinal(Base64.getDecoder().decode(encryptText));
       
        return new String(decryptData, encoding);  
    }

    public static String md5(String value){
        String result = null;
        MessageDigest md5 = null;
        try{
            md5 = MessageDigest.getInstance("MD5");
            md5.update((value).getBytes("UTF-8"));
        }catch (NoSuchAlgorithmException error){
            error.printStackTrace();
        }catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        byte b[] = md5.digest();
        int i;
        StringBuffer buf = new StringBuffer("");

        for(int offset=0; offset<b.length; offset++){
            i = b[offset];
            if(i<0){
                i+=256;
            }
            if(i<16){
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }

        result = buf.toString();
        return result;
    }

    public static String GeneratePasswordHash(String password) throws Exception{
        String method="Jandy:MD532";
        String encPwd= DesUtils.encode(password, ConstConfig.SECRET_KEY);
        String md5Str=md5(method+"$"+encPwd+"$");
        return method+"$"+encPwd+"$"+md5Str;
    }

    public static boolean CheckPasswordHash(String pwdHash,String password) throws Exception{
        String[] aa=pwdHash.split("\\$");
        if (aa.length!=3)
            return false;
//        if (!aa[1].equals(password))
//            return false;
        String encPwd=encode(password,ConstConfig.SECRET_KEY);
        if (!aa[1].equals(encPwd))
            return false;
        return true;

    }


    public static void main(String[] args) throws Exception{
//       String str="7eysqyjhqR8=";
//       String key="abcedefighijklmn12345678";
//       System.out.println(decode(str,key));
//        System.out.println(GeneratePasswordHash(decode(str,key)));
        System.out.println("admin:"+encode("admin", ConstConfig.SECRET_KEY));
        System.out.println("password:"+encode("jandy0414", ConstConfig.SECRET_KEY));

    }
}
