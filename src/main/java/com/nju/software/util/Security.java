package com.nju.software.util;

import javax.crypto.Cipher;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.*;

public class Security {

    public static String encrypt(String input, String key) {

        byte[] crypted = null;

        try {

            input = StringUtils.rightPad(input, 16, "\0");

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

            cipher.init(Cipher.ENCRYPT_MODE, skey);
            crypted = cipher.doFinal(input.getBytes());

        } catch (Exception e) {

            System.out.println(e.toString());

        }

        return new String(Base64.encodeBase64(crypted));

    }

    public static String decrypt(String input, String key) {

        byte[] output = null;

        try {

            SecretKeySpec skey = new SecretKeySpec(key.getBytes(), "AES");

            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");

            cipher.init(Cipher.DECRYPT_MODE, skey);

            output = cipher.doFinal(Base64.decodeBase64(input));



        } catch (Exception e) {

            System.out.println(e.toString());

        }

        return new String(output);

    }



    public static void main(String[] args) {

        String key = "\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0\0";


       // Integer.parseInt("Z",16);
        //String data = "412016278912497";

        String data = "54";

        System.out.println(Security.encrypt(data, key));

        System.out.println(Security.decrypt("gA3l6l+ZMxK5m4EhybV4xA==", key));

        //"ZMxK5m4EhybV4xA=="

    }
}
