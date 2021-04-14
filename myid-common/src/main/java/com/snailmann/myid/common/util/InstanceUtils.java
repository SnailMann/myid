package com.snailmann.myid.common.util;

import java.security.SecureRandom;

/**
 * @author liwenjie
 */
public class InstanceUtils {

    public static String instance = "unknow";

    static {
        SecureRandom random = new SecureRandom();
        instance = "instance-" + random.nextInt(1000) + "-" + random.nextInt(1000);
    }

}
