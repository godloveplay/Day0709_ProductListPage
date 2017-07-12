package com.zmh.utils;

import java.util.UUID;

/**
 * Created by zmh on 2017/7/11.
 */
public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-","");
    }

}
