package org.example;

import java.util.Vector;

public class Producer extends Thread{
    public int length;
    public Buffer buffer;
    public Vector<Integer> vec1, vec2;

    public Producer(Buffer buffer, Vector<Integer> vec1, Vector<Integer> vec2){
        this.buffer = buffer;
        this.vec1 = vec1;
        this.vec2 = vec2;
        this.length = vec1.size();
    }

    @Override
    public void run(){
        for(int i=0; i<length; i++){
            try{
                System.out.printf(Thread.currentThread().getName() + ": Sending %d * %d = %d\n",vec1.get(i),vec2.get(i),vec1.get(i)*vec2.get(i));
                buffer.put(vec1.get(i)*vec2.get(i));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
