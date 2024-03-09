package threading;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintNumbers {

    static final int numbers = 10;
    static AtomicInteger current = new AtomicInteger(0);
    static final Object lock = new Object();

    static class Thread1 implements Runnable {
        @Override
        public void run() {
            print();
        }

        void print() {
            while (current.get() < numbers) {
                synchronized (lock) { // Synchronize on the common lock
                    if (current.get() % 2 == 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + current.getAndIncrement());
                    lock.notify(); // Notify the other thread
                }
            }
        }
    }

    static class Thread2 implements Runnable {
        @Override
        public void run() {
            print();
        }

        void print() {
            while (current.get() <= numbers) {
                synchronized (lock) { // Synchronize on the common lock
                    if (current.get() % 2 != 0) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                        }
                    }
                    System.out.println(Thread.currentThread().getName() + ": " + current.getAndIncrement());
                    lock.notify(); // Notify the other thread
                }
            }
        }
    }

    public static void main(String[] args) {
        Runnable t1 = new Thread1();
        Runnable t2 = new Thread2();

        Thread thread1 = new Thread(t1);
        Thread thread2 = new Thread(t2);

        thread1.start();
        thread2.start();
    }
}
