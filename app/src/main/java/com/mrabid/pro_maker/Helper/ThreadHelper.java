package com.mrabid.pro_maker.Helper;

/**
 * Created by Reaper on 10/15/2017.
 */

public class ThreadHelper extends Thread{

    public void run(int milisecond) throws InterruptedException {
        Thread.sleep(milisecond);
    }
}
