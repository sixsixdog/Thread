package com.sixsixdog;


import java.util.Timer;

/**
 * @Package: com.sixsixdog
 * @ClassName: CMain
 * @Author: Sixsixdog
 * @CreateTime: 2021-03-13 17:33
 * @Description:
 */
public class CMain {
    public static void main(String[] args)
    {
        InitializeThread intia = new InitializeThread();
        intia.start();
        while (intia.getState() != Thread.State.TERMINATED)
        {

        }
    }

}
