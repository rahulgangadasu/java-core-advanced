package java;

//import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

// import java.util.concurrent.locks.Lock;
// import java.util.concurrent.locks.ReentrantLock;

public class DownloadStatus {
    //private volatile boolean isDone; // adding volatile makes jvm read value from main memory not cache.
    //private Lock lock = new ReentrantLock();
    //private int totalBytes;
    // public int getTotalBytes() {
    //     return totalBytes;
    // }
    //private AtomicInteger totalBytes = new AtomicInteger();
    private LongAdder totalBytes = new LongAdder();

    public int getTotalBytes() {
        return totalBytes.intValue();
        //return totalBytes.get(); // for Atomic objects.
    }
    public void incrementTotalBytes(){
        totalBytes.increment();
       //totalBytes.incrementAndGet(); for Atomic Objects.
    }

    // using synchronized 
    // public synchronized void incrementTotalBytes(){
    //     totalBytes++;
    // }
    // public boolean isDone() {
    //     return isDone;
    // }
    // public void done() {
    //     isDone = true;
    // }


    // public synchronized void incrementTotalBytes(){
    //      // adding synchronized keyword = synchronized(this){totalBytes++;}
    //     totalBytes++;
    //     /*-----------for locks------------
    //     lock.lock();
    //     try {
    //         totalBytes++; //non-atmoic operation
    //     }
    //     finally{
    //         lock.unlock();
    //     }*/
    // }
}
