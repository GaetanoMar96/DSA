package threading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * The same instance of FooBar will be passed to two different threads:
 * thread A will call foo(), while
 * thread B will call bar().
 * Modify the given program to output "foobar" n times.
 */
public class FooBar {

    private final int n;
    private volatile boolean isFoo = true;

    private final ExecutorService service;

    public FooBar(int n) {
        this.n = n;
        this.service = Executors.newFixedThreadPool(2);
    }

    public void foo(Runnable printFoo) {
        this.service.submit(() -> {
            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    while (!isFoo) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    printFoo.run();
                    isFoo = false;
                    notify();
                }
            }
        });
    }

    public void bar(Runnable printBar) {
        this.service.submit(() -> {
            for (int i = 0; i < n; i++) {
                synchronized (this) {
                    while (isFoo) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    printBar.run();
                    isFoo = true;
                    notify();
                }
            }
        });
    }

    public void shutdown() {
        this.service.shutdown();
    }
}
