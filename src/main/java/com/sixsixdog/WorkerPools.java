package com.sixsixdog;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;
/**
 * @Package: com.sixsixdog
 * @ClassName: workerQue
 * @Author: Sixsixdog
 * @CreateTime: 2021-03-13 17:44
 * @Description:
 */
public class WorkerPools extends TimerTask {
    private int maximum = 10;
    private int minimum = 2;
    private int waitMaximum = 10;
    private int waitMinimum = 2;
    public   int count=0;
    private int waitCount=0;
    Boolean allWorkComplete =false;
    private Timer waitListSchedule = new Timer();
    ArrayList<CThread> workList = new ArrayList<CThread>();
    ArrayList<String> waitList = new ArrayList<String>();
    protected Logger log = Logger.getLogger(this.getClass().getName());

    public WorkerPools() {
        waitListSchedule.schedule(this,5000,1000);
    }

    @Override
    public void run() {
        if(waitList.size()>0)
        {
                waitListServer(waitList.get(0));
                log.info("a waiter is complete left:"+waitCount);
                if (waitList.size() > 0) {
                    waitList = new ArrayList<String>(waitList.subList(1, waitList.size()));
                } else {
                    waitList.clear();
                    log.info("waitList is All complete");
                }
        }
        else
            {
                log.info("no one in wait");
                if(this.allWorkComplete)
                {
                    log.info("all mission is complete,workPool will be shut down");
                    this.waitListSchedule.cancel();
                }
            }




    }

    protected void refuse(String WorkDescription)
    {
        log.warning("Work description are too busy, this mission is miss：["+WorkDescription+"]");
    }

    public void waitListServer(String WorkDescription)
    {
        if(count<maximum)
        {
            this.workList.add(new CThread(WorkDescription));
        }else
        {
            refuse(WorkDescription);
        }
        waitCount--;
    }
    Boolean addWork(String WorkDescription)
    {
            try
            {
                if(count<maximum)
                {
                    if(count<minimum)
                    {
                        this.workList.add(new CThread(WorkDescription));
                        count++;
                        log.info("add Worker:"+WorkDescription);
                    }
                    else
                    {
                       if(waitCount<waitMaximum)
                       {
                           waitList.add(WorkDescription);
                           waitCount++;
                           log.info("a Worker has been put in wait list:"+WorkDescription+" wait count:"+waitCount);
                       }
                       else
                       {
                           refuse(WorkDescription);
                       }
                    }

                }
                else
                {
                    refuse(WorkDescription);
                }


            }catch (Exception e)
            {
                return false;
            }
            return true;
    }

}
