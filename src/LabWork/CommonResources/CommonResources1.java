package LabWork.CommonResources;

import java.util.Stack;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.Semaphore;

/**
 * Created by Александр Сергеевич on 14.04.2016.
 */

public class CommonResources1
{
    private Stack<Integer> Resource = new Stack<>();

    synchronized public void put(int IntegerVar)
    {
        Resource.push(IntegerVar);
        System.out.println("Push method: " + IntegerVar);
        try {
            Thread.sleep(1000);
        }
        catch (Exception ex)
        {System.out.println("Ex");}
        notifyAll();
    }

    synchronized public Integer get()
    {
        while (Resource.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Integer IntegerVar = Resource.pop();
        System.out.println("Pop method: " + IntegerVar);
        return IntegerVar;
    }
}
