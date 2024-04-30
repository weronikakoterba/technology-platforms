package org.example;

public class Calculation implements Runnable{
    private BlockingQueue<Integer> data;
    private BlockingQueue<String> result;
    private String name;
    public Calculation(BlockingQueue<Integer> data,BlockingQueue<String> result,String name){
        this.data=data;
        this.result=result;
        this.name=name;
    }
    public Integer is_prime(int number) throws InterruptedException {
        if(number==1 || number==2) return 1;
        for(int i=2;i<number;i++){
            if(number%i==0){
                Thread.sleep(1000);
                return 1;
            }
        }
        Thread.sleep(1000);
        return 0;
    }
    public void run() {
        while (true) {
            try {
                int n = data.take();
                if (is_prime(n)==1) {
                    result.put(n + " is not a prime number, checked by " +name);
                } else {
                    result.put(n + " is a prime number, checked by " +name);
                 }
            }
            catch (InterruptedException e) {
                break;
            }
        }
    }
}
