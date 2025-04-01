package Utils;

public class Test extends Thread {
    public boolean stop = false;
    private int seconds = 0;
    public void run() {
        while (!stop) {
            try {
                Thread.sleep(1000);
                System.out.println("Time elapsed: " + seconds);
                seconds++;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void restartTimer() {
        this.seconds = 0;
    }
}
