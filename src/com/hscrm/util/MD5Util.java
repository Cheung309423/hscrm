package com.hscrm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Peter Cheung
 * @user PerCheung
 * @date 2021/8/31 22:41
 */
public class MD5Util {
    public static String toMd5(String oldPassword) {
        MessageDigest md5 = null;
        String result = "";
        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] newPassword = md5.digest(oldPassword.getBytes());
            for (byte b : newPassword) {
                String temp = Integer.toHexString(b & 0xff);
                if (temp.length() == 1) {
                    temp = "0" + temp;
                }
                result += temp;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return result;
    }
}
