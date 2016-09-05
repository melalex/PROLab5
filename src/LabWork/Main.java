package LabWork;

import LabWork.CommonResources.*;

import java.io.*;
import java.io.PrintWriter;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    final static private int tacts = 10;

    public static void main(String[] args)
    {
        CommonResources1 commonResources1 = new CommonResources1();
        CommonResources2 commonResources2 = new CommonResources2();

        Semaphore semaphore_1 = new Semaphore(0);
        Semaphore semaphore_2 = new Semaphore(0);

        CyclicBarrier cyclicBarrier_1 = new  CyclicBarrier(2);
        CyclicBarrier cyclicBarrier_2 = new  CyclicBarrier(2);

        ReentrantLock reentrantLock = new ReentrantLock();

        try(PrintWriter reportFile = new PrintWriter(new File("report.log")))
        {
            SimpleTread thread1_provider = new SimpleTread("Provider1", ()->
            {
                for(int i = 0; ; i++)
                {
                    try{
                        System.out.println("Thread Provider1 wait cyclicBarrier_1");
                        cyclicBarrier_1.await();
                    }
                    catch(InterruptedException | BrokenBarrierException e){
                        System.out.println("thread1_provider IS PANICKING!!! " + e);
                    }
                    System.out.println("Thread Provider1 put to commonResources1");
                    commonResources1.put(1);

                    System.out.println("Thread Provider1 wait reentrantLock");
                    reentrantLock.lock();

                    System.out.println("Thread Provider1 put to commonResources2");
                    commonResources2.put(true);
                    commonResources2.put((byte)i);
                    commonResources2.put((char)i);
                    commonResources2.put((float)0.1 + i);
                    reportFile.println(commonResources2);
                    reentrantLock.unlock();
                }
            });

            SimpleTread thread2_consumer = new SimpleTread("Consumer2",()->
            {
                for(;;)
                {
                    try{
                        System.out.println("Thread Provider1 wait cyclicBarrier_2");
                        cyclicBarrier_2.await();
                    }
                    catch(InterruptedException | BrokenBarrierException e){
                        System.out.println("thread2_consumer IS PANICKING!!! " + e);
                    }

                    System.out.println("Thread Consumer2 get from commonResources1: " + commonResources1.get());

                    System.out.println("Thread Consumer2 wait reentrantLock");
                    reentrantLock.lock();

                    System.out.println("Thread Consumer2 get from commonResources2");
                    commonResources2.getBooleanVar();
                    commonResources2.getByteVar();
                    commonResources2.getCharacterVar();
                    commonResources2.getFloatVar();
                    commonResources2.getIntegerVar();
                    commonResources2.getLongVar();
                    commonResources2.getShortVar();
                    commonResources2.getDoubleVar();
                    reentrantLock.unlock();
                }
            });

            SimpleTread thread3_provider = new SimpleTread("Provider3", ()->
            {
                for(int i = 0; ; i++)
                {
                    System.out.println("Thread Provider3 wait reentrantLock");
                    reentrantLock.lock();

                    System.out.println("Thread Provider3 put to commonResources2");
                    commonResources2.put(i);
                    commonResources2.put((long)i);
                    commonResources2.put((short)i);
                    commonResources2.put(0.3 + i);
                    reportFile.println(commonResources2);
                    reentrantLock.unlock();

                    try{
                        System.out.println("Thread Provider3 wait cyclicBarrier_1");
                        cyclicBarrier_1.await();
                    }
                    catch(InterruptedException | BrokenBarrierException e){
                        System.out.println("thread3_provider IS PANICKING!!! " + e);
                    }
                    System.out.println("Thread Provider3 put to commonResources1");
                    commonResources1.put(3);
                }
            });

            SimpleTread thread4_provider = new SimpleTread("Provider4", ()->
            {
                for(;;)
                {
                    try {
                        System.out.println("Thread Provider4 release semaphore_2");
                        semaphore_2.release();

                        System.out.println("Thread Provider4 wait semaphore_1");
                        semaphore_1.acquire();

                        System.out.println("Thread Provider4 put to commonResources1");
                        commonResources1.put(4);

                        System.out.println("Thread Provider4 wait cyclicBarrier_2");
                        cyclicBarrier_2.await();
                    }
                    catch(InterruptedException | BrokenBarrierException e){
                        System.out.println("thread4_provider IS PANICKING!!! " + e);
                    }
                }
            });

            SimpleTread thread5_provider = new SimpleTread("Provider5", ()->
            {
                for(;;)
                {
                    try{
                        System.out.println("Thread Provider5 release semaphore_1");
                        semaphore_1.release();

                        System.out.println("Thread Provider5 wait semaphore_2");
                        semaphore_2.acquire();

                        System.out.println("Thread Provider5 put to commonResources1");
                        commonResources1.put(5);
                    }
                    catch(InterruptedException e){
                        System.out.println("thread5_provider IS PANICKING!!! " + e);
                    }
                }
            });

            thread1_provider.join();
            thread2_consumer.join();
            thread3_provider.join();
            thread4_provider.join();
            thread5_provider.join();
        }
        catch(InterruptedException | FileNotFoundException e)
        {
            System.out.println("main IS PANICKING!!! " + e);
        }
    }
}
