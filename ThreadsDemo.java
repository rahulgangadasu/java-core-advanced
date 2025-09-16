package java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
//import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// import java.util.ArrayList;
// import java.util.List;

public class ThreadsDemo {
    public static void main(String[] args) {
        /* --------------------------------synchronized & concurrent collections----------------------- */
        Collection <Integer> collection = Collections.synchronizedCollection(new ArrayList<>());
        Thread thread1 = new Thread(() -> {
            collection.addAll(Arrays.asList(1, 2, 3));
        });

        Thread thread2 = new Thread(() -> {
            collection.addAll(Arrays.asList(4, 5, 6));
        });
        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(collection);

        Map <Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "rahul");

        /*------------------------------------Atomic Objects & Adders------------------------------------- 
        var status = new DownloadStatus();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            var thread = new Thread(new DownloadFileTask(status));
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(status.getTotalBytes()); */


        /*---------------------------volatile approach------------------------------------------- 
        var status = new DownloadStatus();
        var thread1 = new Thread(new DownloadFileTask(status));
        var thread2 = new Thread(() -> {
                                        while(!status.isDone()){
                                            synchronized(status){
                                                try {
                                                    status.wait();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                        System.out.println(status.getTotalBytes());
                                        }); //defining run method as anonymus inner class.
        thread1.start();
        thread2.start(); */

        /*----------------------------------Locks & synchronized ---------------------------------- 
        List<Thread> threads = new ArrayList<>();
        var status = new DownloadStatus();
        for (int i = 0; i < 10; i++) {
            var thread = new Thread(new DownloadFileTask(status));
            thread.start();
            threads.add(thread);
       }
       for (Thread thread : threads) {
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       }
       System.out.println("Total bytes downloaded : " + status.getTotalBytes()); */


        /*------------------------Confinement ------------------------------------------ 
        List<Thread> threads = new ArrayList<>();
        List<DownloadFileTask> tasks = new ArrayList<>(); 
        for (int i = 0; i < 10; i++) {
            var task = new DownloadFileTask();
            tasks.add(task);
            Thread thread = new Thread(task);
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        var totalBytesDownloaded = tasks.stream()
                                          .map(t -> t.getStatus().getTotalBytes())
                                          .reduce(Integer :: sum);
        System.out.println("Total Bytes after confinement : " + totalBytesDownloaded.orElse(0));*/


       /* ------------------------Race Condition--------------------------------------------
        DownloadStatus status = new DownloadStatus();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(new DownloadFileTask(status));
            thread.start();
            threads.add(thread);
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Total bytes downloaded : " + status.getTotalBytes()); */

        /*  System.out.println(Thread.activeCount());
            System.out.println(Runtime.getRuntime().availableProcessors());
            System.out.println(Thread.currentThread().getName()); */

        /*  ------------------------Interrupted code----------------------------------------
            Thread thread = new Thread(new DownloadFileTask());
            thread.start();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            thread.interrupt(); // just sends a request - is thread interrupted. doesn't force thread to stop.
            try {
               thread.join();
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println("File is ready to scan."); */

    }
}
