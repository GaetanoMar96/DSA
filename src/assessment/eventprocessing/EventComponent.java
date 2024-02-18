package assessment.eventprocessing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Your task is to develop a component that will be deployed as part of a distributed, event-driven
 * system.
 * Your component will receive events from a source, process them and publish a resulting message
 * to a sink.
 * In this exercise the processing will consist of hashing each message value received in
 * onMessage() concatenating with the most recently received salt value received in onSalt(),
 * using a total of 5000 applications of SHA256:
 * hash = sha256( ... sha256( sha256( message || salt ) || salt ) ... || salt )
 * Your component must call publishHash() for each message received in onMessage() with
 * the resulting hash value.
 * Optimize your component to minimize the processing latency and achieve a high throughput.
 */
public class EventComponent implements Observer {

    private final Sink sink;

    private byte[] salt;

    public EventComponent(Sink sink) {
        this.sink = sink;
    }

    @Override
    public void onSalt(byte[] salt) {
        this.salt = salt;
    }

    @Override
    public void onMessage(long id, byte[] message) {
        if(this.salt != null) {
            byte[] hashedMessage = hashMessageWithSalt(message);
            this.sink.publishHash(id, message, this.salt, hashedMessage);
        }
    }

    private byte[] hashMessageWithSalt(byte[] message) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] concatenatedArray = concatenateArrays(message, this.salt);
            concatenatedArray = messageDigest.digest(concatenatedArray);
            for(int i = 0; i < 4999; i++) {
                concatenatedArray = concatenateArrays(concatenatedArray, this.salt);
                concatenatedArray = messageDigest.digest(concatenatedArray);
            }
            return concatenatedArray;
        } catch(NoSuchAlgorithmException e) {
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
