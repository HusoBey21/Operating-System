import java.lang.System.Logger;
import java.lang.System.Logger.Level;

public class sushiiki implements Runnable {

    private String threadName;
    private sushibar bar;
    private int kisisayisi;

    int sandalye = 5;

    public sushiiki(String threadName, sushibar bar, int kisisayisi) {
        this.threadName = threadName;
        this.bar = bar;
        this.kisisayisi = kisisayisi;

    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Hata");
            }
            bar.mutexAcquire();
            if (bar.getmustWait()) {
                bar.setWaiting(bar.getWaiting() + 1);
                System.out.println("Bekleyen kisi:" + threadName);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Hata");
                }
                bar.mutexRelease();
                bar.blockAcquire();
            } else {
                bar.setEating(bar.getEating() + 1);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.out.println("Hata");
                }
                System.out.println("Yiyen kisi:" + threadName);

                bar.setmustWait(bar.getEating() == 5);
                bar.mutexRelease();
            }

            System.out.println(bar.getEating() + "kisi yiyor" + (sandalye - bar.getEating()) + "sandalye bosta");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Hata");
            }
            bar.mutexAcquire();
            bar.setEating(bar.getEating() - 1);
            System.out.println("Ayrilan kisi:" + threadName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Hata");
            }
            System.out.println(bar.getEating() + "kisi yiyor" + (sandalye - bar.getEating()) + "sandalye bosta");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("Hata");
            }
            if (bar.getEating() == 0) {
                int n = Math.min(5, bar.getWaiting());
                bar.setWaiting(bar.getWaiting() - n);
                bar.setEating(bar.getEating() + n);
                bar.setmustWait(bar.getEating() == 5);
                for (int s = 0; s < n; s++) {
                    bar.blockRelease();
                }

            }
            bar.mutexRelease();
        }

    }
}
