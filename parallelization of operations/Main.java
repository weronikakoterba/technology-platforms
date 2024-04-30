package org.example;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//https://www.baeldung.com/java-when-to-use-parallel-stream

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        List<Path> files = null;
        Path source = Path.of("C:\\Users\\DELL\\IdeaProjects\\lab6\\Obrazy cw 6 Java\\Obrazy cw 6 Java");
        try (Stream<Path> stream = Files.list(source)){
            files = stream.collect(Collectors.toList());
        }catch(IOException e) {
            e.printStackTrace();
        }

        Photos photo=new Photos();
        for(int i=1;i<=5;i++) {
            long time = System.currentTimeMillis();
            ForkJoinPool pool = new ForkJoinPool(i);
            assert files != null;
            List<Path> finalFiles = files;
            pool.execute(()->{
                    finalFiles.parallelStream()
                            .map(photo::get_path_and_photo)
                            .map(photo::modify_photo)
                            .forEach(photo::save_image);
            });
            pool.shutdown();
            try{
               pool.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("ilość wątków: "+i+", czas wykonania: "+ (System.currentTimeMillis() - time));

        }
    }
}