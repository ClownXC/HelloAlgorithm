package cs;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Semaphore;

public class ProducerAndConsumer {


    private static Integer count = 0;
    final static BlockingQueue blockingQueue = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try { Thread.sleep(3000);} catch (Exception e) { e.printStackTrace();}
                try {
                    blockingQueue.put(1);
                    count++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try { Thread.sleep(3000);} catch (Exception e) { e.printStackTrace();}
                try {
                    blockingQueue.take();
                    count--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    public void SemaphoreExample(){

        final Semaphore full = new Semaphore(10);
        final Semaphore empty = new Semaphore(0);
        final Semaphore mutex = new Semaphore(1);


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try { Thread.sleep(3000);} catch (Exception e) { e.printStackTrace();}
                try {
                    blockingQueue.put(1);
                    count++;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();


        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                try { Thread.sleep(3000);} catch (Exception e) { e.printStackTrace();}
                try {
                    blockingQueue.take();
                    count--;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
