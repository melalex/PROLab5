package LabWork;

/**
 * Created by Александр Сергеевич on 22.04.2016.
 */

public class SimpleTread
{
    private Thread _thread;

    SimpleTread(String name, Runnable quest)
    {
        _thread = new Thread(quest, name);
        System.out.println("Start thread " + name);
        _thread.start();
    }

    void join() throws InterruptedException
    {
        _thread.join();
    }
}
