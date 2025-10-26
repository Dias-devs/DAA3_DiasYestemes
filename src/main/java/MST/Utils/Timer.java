package MST.Utils;

public class Timer {
    private long start;

    public void start() { start = System.nanoTime(); }

    public double elapsedMillis() {
        long now = System.nanoTime();
        return (now - start) / 1_000_000.0;
    }
}