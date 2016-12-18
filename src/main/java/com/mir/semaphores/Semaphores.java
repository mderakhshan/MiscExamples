package com.mir.semaphores;

/**
 * Created by Mir on 08/10/2016.
 */
public class Semaphores {
    public static class BoundedSemaphore {
        private int signals = 0;
        private int bound   = 0;

        public BoundedSemaphore(int upperBound){
            this.bound = upperBound;
        }

        public synchronized void take() throws InterruptedException{
            while(this.signals == bound) wait();
            this.signals++;
            this.notify();
        }

        public synchronized void release() throws InterruptedException{
            while(this.signals == 0) wait();
            this.signals--;
            this.notify();
        }
    }

    public static void main (String[] args) {
        Runnable tesRunner = new Runnable() {
            int counter = 0;
            @Override
            public void run() {
                BoundedSemaphore s = new BoundedSemaphore(1);
                try {
                    s.take();
                } catch (InterruptedException e) {
                }

                try{
                    counter++;
                    System.out.println  ("Counter = " + counter);
                } finally {
                    try {
                        s.release();
                    } catch (InterruptedException e) {
                    }
                }

            }
        };
        Thread t1 = new Thread (tesRunner);
        Thread t2 = new Thread (tesRunner);
        t1.start();
        t2.start();
    }
}
