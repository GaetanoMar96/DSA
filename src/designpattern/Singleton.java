package designpattern;

public class Singleton {

    private static Singleton instance;

    private Singleton() {

    }

    /**
     * Method to implement the singleton pattern. If an instance already exists then return it
     * @return a singleton instance
     */
    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
