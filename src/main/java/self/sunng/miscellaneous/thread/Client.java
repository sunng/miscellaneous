package self.sunng.miscellaneous.thread;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start");
        Thread[] objects = new Thread[3];
        for (int i = 0; i < 3; i++) {
            SubThread subThread = new SubThread("sub" + i);
            subThread.start();
            objects[i] = subThread;
            // subThread.join();
        }
        Thread.sleep(500);
        System.out.println(objects[0].getState());
        System.out.println(objects[1].getState());
        System.out.println(objects[2].getState());
        Thread.sleep(500);

        // for (int i = 0; i < 3; i++) {
        //     System.out.println("nodify " + i);
        //     synchronized (objects[i]) {
        //         objects[i].notify();
        //     }
        // }

        // synchronized (Client.class) {
        //     LockSupport.unpark(objects[0]);
        //     LockSupport.unpark(objects[1]);
        //     LockSupport.unpark(objects[2]);
        //     Client.class.notifyAll();
        //     Thread.sleep(100);
        //     System.out.println(objects[0].getState());
        //     System.out.println(objects[1].getState());
        //     System.out.println(objects[2].getState());
        //     Thread.sleep(100);
        // }
        System.out.println("main end");
    }
}
