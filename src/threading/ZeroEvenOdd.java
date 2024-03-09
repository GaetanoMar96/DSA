package threading;

import java.util.function.IntConsumer;

/**
 * Modify the given class to output the series "010203040506..." where the length of the series must be 2n.
 * Implement the ZeroEvenOdd class:
 * ZeroEvenOdd(int n) Initializes the object with the number n that represents the numbers that should be printed.
 * void zero(printNumber) Calls printNumber to output one zero.
 * void even(printNumber) Calls printNumber to output one even number.
 * void odd(printNumber) Calls printNumber to output one odd number.
 */
class ZeroEvenOdd {
    private final int n;
    private final Object lock = new Object();
    private volatile int current = 1;
    private volatile boolean isZero = true;


    public ZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 0; i < n; i++) {
            synchronized(lock) {
                while(!isZero)
                    lock.wait();
                printNumber.accept(0);
                isZero = false;
                lock.notifyAll();
            }
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2; i <= n; i+=2) {
            synchronized(lock) {
                while(isZero || (current % 2 != 0))
                    lock.wait();
                printNumber.accept(current++);
                isZero = true;
                lock.notifyAll();
            }
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1; i <= n; i+=2) {
            synchronized(lock) {
                while(isZero || (current % 2 == 0))
                    lock.wait();
                printNumber.accept(current++);
                isZero = true;
                lock.notifyAll();
            }
        }
    }
}
