package comparator;

public class ItemComparable implements Comparable<Integer>{

    public int value;

    public ItemComparable(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(Integer o) {
        return value - o;
    }

    public int getValue() {
        return value;
    }
}
