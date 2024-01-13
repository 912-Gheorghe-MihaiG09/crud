package org.example;
import java.util.Arrays;
import java.util.Vector;


public class Main {
    public static void main(String[] args) {

        Vector<Integer> vec1 = new Vector<Integer>(Arrays.asList(1,2,3,4,5));
        Vector<Integer> vec2 = new Vector<Integer>(Arrays.asList(5,4,3,2,1));

        Vector<Integer> vec3 = new Vector<Integer>();
        Vector<Integer> vec4 = new Vector<Integer>();

        for (int i = 1; i <= 100; i++) {
            vec3.add(i);
            vec4.add(1);
        }



        Buffer buffer = new Buffer();
//        Producer producer = new Producer(buffer,vec1,vec2);
//        Consumer consumer = new Consumer(buffer,vec1.size());
        Producer producer = new Producer(buffer,vec3,vec4);
        Consumer consumer = new Consumer(buffer,vec3.size());

        producer.start();
        consumer.start();
    }
}
