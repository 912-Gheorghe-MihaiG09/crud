package org.example;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Buffer {
    private static final int CAPACITY = 1;
    private final Queue<Integer> queue = new LinkedList<>();

    private final Lock lock = new ReentrantLock();
    private final Condition condVar = lock.newCondition();

    public void put(int val) throws InterruptedException {
        lock.lock();
        try {
            while(queue.size() == CAPACITY){
                System.out.println(Thread.currentThread().getName()
                        + ": Queue is full, waiting...");
                condVar.await();
            }
            queue.add(val);
            System.out.printf("%s added %d into the queue %n", Thread
                    .currentThread().getName(), val);

            condVar.signal();
        } finally {
            lock.unlock();
        }
    }
    public int get() throws InterruptedException {
        lock.lock();
        try{
            while(queue.isEmpty()){
                System.out.println(Thread.currentThread().getName()
                        + ": queue is empty, waiting");
                condVar.await();
            }
            Integer value = queue.poll();
            if(value!=null){
                System.out.printf(Thread
                        .currentThread().getName() + " consumed %d from the queue %n", value);
                condVar.signal();
            }
            return value;
        } finally {
            lock.unlock();
        }
    }
}