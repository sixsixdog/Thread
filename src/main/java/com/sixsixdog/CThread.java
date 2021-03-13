package com.sixsixdog;

import java.util.ArrayList;
import java.util.Timer;

/**
 * @Package: com.sixsixdog
 * @ClassName: Thread
 * @Author: Sixsixdog
 * @CreateTime: 2021-03-13 17:09
 * @Description:
 */
public class CThread extends Thread {
    private String Description;

    CThread(String WorkDescription){
        this.Description = WorkDescription;
    }


    public void run() {
        System.out.println(this.Description);
    }
}
