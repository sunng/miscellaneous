package self.sunng.miscellaneous.thread;

import java.util.concurrent.locks.LockSupport;

public class SubThread extends Thread {
    private String name;

    public SubThread(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        System.out.println(name + " start");

        LockSupport.park(Client.class);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // try {
            // Thread.sleep(1000);
            // LockSupport.park();
            // synchronized (Client.class) {
            //     Client.class.wait();
            // }
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }

        System.out.println(name + " end");
    }
}
