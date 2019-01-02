public class Counter {

    private int count;

    private final int initialValue;

    Counter(int initialValue) {
        count = initialValue;
        this.initialValue = initialValue;
    }

    public synchronized void addOne() {
        count = count + 1;
    }

    public void reset() {
        count = initialValue;
    }

    public int getCount() {
        return count;
    }
}
