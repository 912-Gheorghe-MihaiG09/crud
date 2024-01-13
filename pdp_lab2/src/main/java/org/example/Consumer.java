package org.example;

public final class Consumer extends Thread{
    public int result = 0;
    public Buffer buffer;
    public int length;

    public Consumer(Buffer buffer, int length){
        this.buffer = buffer;
        this.length = length;
    }

    @Override
    public void run() {
        for(int i = 0; i < this.length; i++){
            try {
                result += buffer.get();
                System.out.printf(Thread.currentThread().getName() + ": current sum %d\n", result);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.printf("\n" + Thread.currentThread().getName() + ": Final sum is: %d", result);
    }
}