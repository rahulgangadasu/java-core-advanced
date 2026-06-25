package java_code;

public class DownloadFileTask implements Runnable{
    private DownloadStatus status;
    
    public DownloadFileTask(DownloadStatus status) {
        this.status = status;
    }

    public void run(){
        for (int i = 0; i < 10_000; i++) {
           status.incrementTotalBytes();
        }
    }
    
    public DownloadStatus getStatus() {
        return status;
    }

    // wait() and notify()
    // public void run() {
    //     for (int i = 0; i < 1_000_000; i++) {
    //         status.incrementTotalBytes();
    //     }
    //     status.done();
    //     synchronized(status){
    //         status.notify();
    //     }
        
    // }

    
    // confinement race condition
    // public DownloadFileTask(){
    //     this.status = new DownloadStatus();
    // }

    // for race condition.
    // public DownloadFileTask(DownloadStatus status) {
    //     this.status = status; 
    // }
    //@SuppressWarnings("static-access")
     /* ----------------------------------------------------------------------
        System.out.println("Downloading a File : " + Thread.currentThread().getName());
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            if(Thread.currentThread().interrupted()) return; //checks for interruption, if not found continues forever.
            System.out.println("Bytes Downloaded : " + i);
        }
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace(); 
        } */
}
