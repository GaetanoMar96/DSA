package assessment.eventprocessing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.*;

public class MultiEventComponent implements Observer {

    private final Sink sink;

    private byte[] salt;

    private final ExecutorService executorService;

    private final LinkedBlockingDeque<Task> deque;

    private volatile boolean flag = true;

    public MultiEventComponent(Sink sink) {
        this.sink = sink;
        this.executorService = Executors.newFixedThreadPool(10);
        this.deque = new LinkedBlockingDeque<>();
        processEvent();
    }

    static class Task {
        private final long id;
        private final byte[] message;

        public Task(long id, byte[] message) {
            this.id = id;
            this.message = message;
        }
    }

    @Override
    public void onSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public void onMessage(long id, byte[] message) {
        this.deque.add(new Task(id, message));
    }

    private void processEvent() {
        this.executorService.submit(
                () -> {
                    try {
                        while (flag) {
                            Task task = deque.take();
                            executorService.submit(() -> processEvent(task));
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    } finally {
                        executorService.shutdown();
                    }
                });
    }

    private void processEvent(Task task) {
        byte[] hashedMessage = hashMessageWithSalt(task.message);
        sink.publishHash(task.id, task.message, this.salt, hashedMessage);
    }

    private byte[] hashMessageWithSalt(byte[] message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] concatenatedArray = concatenateArrays(message, this.salt);
            concatenatedArray = messageDigest.digest(concatenatedArray);
            for (int i = 0; i < 4999; i++) {
                concatenatedArray = concatenateArrays(concatenatedArray, this.salt);
                concatenatedArray = messageDigest.digest(concatenatedArray);
            }
            return concatenatedArray;
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No algorithm exception");
            throw new RuntimeException();
        }
    }

    private byte[] concatenateArrays(byte[] array1, byte[] array2) {
        byte[] concatenatedArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, concatenatedArray, 0, array1.length);
        System.arraycopy(array2, 0, concatenatedArray, array1.length, array2.length);
        return concatenatedArray;
    }
}
