package org.example;

import java.util.Scanner;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int t_number=0;
        if(args.length>0 && Integer.parseInt(args[0])>0 ){
            t_number= Integer.parseInt(args[0]);
            //System.out.println(t_number);
        }
        else
            return;

        BlockingQueue<Integer> data = new BlockingQueue<>();
        BlockingQueue <String> result = new BlockingQueue<>();
        Vector<Thread> threads = new Vector<>();

        for (int i = 0; i < t_number; i++) {
            Runnable r = new Calculation(data, result, "Thread " + i);
            Thread t = new Thread(r);
            threads.add(t);
            threads.get(i).start();
        }
//        data.put(222);
//        data.put(222);
//        data.put(222);
//        data.put(222);
//        data.put(222);

        Scanner scanner = new Scanner(System.in);
        System.out.println("menu:\n1)check if given number is prime\n2)print queue of results\n3)quit");
        while (true) {
            int s = scanner.nextInt();
            if (s==1) {
                int n = scanner.nextInt();
                scanner.nextLine();
                data.put(n);
            } else if (s==2) {
                result.print_queue();
            } else if(s==3) {
                for(int i=0;i<threads.size();i++){
                    threads.get(i).interrupt();
                }
                return;
            }
        }
    }
}