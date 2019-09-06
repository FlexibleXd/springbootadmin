package com.flexible.springbootadmin.util;

import org.apache.shiro.crypto.hash.Sha256Hash;

public class PwdUtils {

    public static String getHashPwd(String password, String salt) {
        return new Sha256Hash(password, salt).toHex();
    }
}
