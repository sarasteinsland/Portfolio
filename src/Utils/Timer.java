package Utils;

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
}
