
package Utils;

public class Timer {
    private long start;
    private long end;

    public void start() {
        start = System.nanoTime();
    }


    public void stopAndPrint(String msg) {
        end = System.nanoTime();
        long elapsedTime = (end - start) / 1_000_000;
        System.out.printf("%s %d ms\n", msg, elapsedTime);
    }

}

