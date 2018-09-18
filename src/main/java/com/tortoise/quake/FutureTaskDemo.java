package com.tortoise.quake;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 
 * @ClassName:FutureTaskDemo
 * @Description:FutureTask实现多线程并发执行任务并取结果归集
 * @author diandian.zhang
 * @date 2017年6月16日上午10:36:05
 */
public class FutureTaskDemo {
    
    public static void main(String[] args)  {
        Long start = System.currentTimeMillis();
        //开启多线程
        ExecutorService exs = Executors.newFixedThreadPool(5);
        try {
            //结果集
            List<Integer> list = new ArrayList<Integer>();
            List<FutureTask<Integer>> futureList = new ArrayList<FutureTask<Integer>>();
            //启动线程池，10个任务固定线程数为5
            for(int i=0;i<10;i++){
                 FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableTask(i+1));
                //提交任务，添加返回
                exs.submit(futureTask);//Runnable特性
                futureList.add(futureTask);//Future特性
            }
            Long getResultStart = System.currentTimeMillis();
            System.out.println("结果归集开始时间="+new Date());
            //结果归集
            for (FutureTask<Integer> future : futureList) {
            	
                while (true) {
                    if (future.isDone()&& !future.isCancelled()) {
                        Integer i = future.get();//Future特性
                        System.out.println("i="+i+"获取到结果!"+new Date());
                        list.add(i);
                        break;
                    }else {
                        Thread.sleep(1);//避免CPU高速轮循，可以休息一下。
                    }
                }
            }
            System.out.println("list="+list);
            System.out.println("总耗时="+(System.currentTimeMillis()-start)+",取结果归集耗时="+(System.currentTimeMillis()-getResultStart));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();
        }
        
    }

    static class CallableTask implements Callable<Integer>{
        Integer i;
        
        public CallableTask(Integer i) {
            super();
            this.i=i;
        }

        @Override
        public Integer call() throws Exception {
            if(i==1){
                Thread.sleep(3000);//任务1耗时3秒
            }else if(i==5){
                Thread.sleep(5000);//任务5耗时5秒
            }else{
                Thread.sleep(1000);//其它任务耗时1秒
            }
            System.out.println("线程：["+Thread.currentThread().getName()+"]任务i="+i+",完成！"+new Date());  
            return i;

        }
        
    }

}