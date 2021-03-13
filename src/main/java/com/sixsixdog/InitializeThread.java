package com.sixsixdog;

/**
 * @Package: com.sixsixdog
 * @ClassName: InitializeThread
 * @Author: Sixsixdog
 * @CreateTime: 2021-03-13 19:03
 * @Description:
 */
public class InitializeThread extends Thread {
    WorkerPools wp = new WorkerPools();
    @Override
    public void run() {

        for (int i = 0; i <36;i++)
        {
            wp.addWork("Worker:"+i);
            try {
                sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        wp.allWorkComplete = true;
    }

}
