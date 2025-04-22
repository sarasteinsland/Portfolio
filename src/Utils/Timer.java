/*package Utils;

public class Timer extends Thread {
    private boolean stop = false;
    private int seconds = 0;
    private long start;
    private long end;
    private volatile boolean pause = false;

    public void run() {
        start = System.currentTimeMillis();
        while (!stop) {
            while (pause) Thread.onSpinWait();
            try {
                System.out.println("Time elapsed: " + seconds + " s");
                seconds++;
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void interval() {
        this.start = System.currentTimeMillis();
        this.pause = false;
    }

    public void printInterval(String msg) {
        end = System.currentTimeMillis();
        System.out.printf("%s %d ms\n", msg, (end - start));
        this.seconds = 0;
        this.pause = true;
    }

    public void stopTimer() {
        this.stop = true;
    }
}*/
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


    public long getInterval() {
        return (System.nanoTime() - start) / 1_000_000;
    }
}

