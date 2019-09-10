package com.flexible.springbootadmin.util;

import java.sql.Timestamp;
import java.util.Date;

public class AppUtils {
    public static Timestamp getNowTime() {
        return new Timestamp(new Date().getTime());
    }
}
