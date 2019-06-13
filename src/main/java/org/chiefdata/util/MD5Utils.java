package org.chiefdata.util;

import java.security.MessageDigest;

/**
 * @author Kevin
 * @Title: MD5Utils
 * @ProjectName data-structure
 * @Description: TODO
 * @date 2019/6/12 20:33
 */
public class MD5Utils {
    private static final MessageDigest MD = null;

    static {
        try {
            MessageDigest MD = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String MD5Encode(String origin) {
        byte[] digest = MD.digest(origin.getBytes());
        return null;
    }
}