package com.primemobi.iaas.util;

/**
 * Created with IntelliJ IDEA.
 * User: George
 * Date: 13-4-23
 * Time: 下午1:32
 */
public class VmStatus {

    public static enum Status{
        pending,running,paused ,terminated,powerOff,noState,blocked;
    }


}
