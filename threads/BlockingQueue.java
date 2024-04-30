package org.example;

import java.util.LinkedList;
import java.util.Queue;
//Blocking queue napisana z pomocą:
//https://www.devglan.com/datastructure/blocking-queue-implementation-in-java
//https://www.youtube.com/watch?v=UOr9kMCCa5g&t=596s

public class BlockingQueue<v> {
    private Queue<v> q;
    public BlockingQueue(){
        q=new LinkedList<>();
    }
    public synchronized void put( v data) throws InterruptedException {
        q.add(data);
        notifyAll();
    }
    public synchronized v take() throws InterruptedException{
            while(q.isEmpty()){
                wait();
            }
        return q.remove();
    }
    public void print_queue(){
        q.forEach(System.out::println);
    }
}
