package com.sfu.cmpt470.Util;

import org.apache.commons.codec.digest.DigestUtils;

public class StringUtil {
    public static boolean isNullOrEmpty(String str){
        return str == null || str.equals("");
    }

    public static String SHA1Hash(String input){
        return DigestUtils.sha1Hex(input);
    }
}
