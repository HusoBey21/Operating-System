
import java.util.logging.Level;
import java.util.logging.Logger;

public class sushi implements Runnable {
    private String threadName;
    private sushibar bar;
    private int kisisayisi;

    int sandalye = 5;

    public sushi(String threadName, sushibar bar, int kisisayisi) {
        this.threadName = threadName;
        this.bar = bar;
        this.kisisayisi = kisisayisi;

    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(sushi.class.getName()).log(Level.SEVERE, null, ex);
            }

            bar.mutexAcquire();
            if (bar.getmustWait() == true) {
                bar.setWaiting(bar.getWaiting() + 1);
                System.out.println("Bekleyen kisi :" + threadName);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(sushi.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.out.println("Bekleyen kisi sayisi:" + bar.getWaiting());
                bar.mutexRelease();
                bar.blockAcquire();
                bar.setWaiting(bar.getWaiting() - 1);
                System.out.println("Bekleyen kisi sayisi:" + bar.getWaiting());
                bar.setmustWait(false);
            }

            bar.setEating(bar.getEating() + 1);
            System.out.println("Yiyen kisi:" + threadName);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(sushi.class.getName()).log(Level.SEVERE, null, ex);

            }
            System.out.println(bar.getEating() + "kisi yemekte" + (sandalye - bar.getEating()) + "sandalye bosta");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(sushi.class.getName()).log(Level.SEVERE, null, ex);
            }
            bar.setmustWait(bar.getEating() == 5);

            if (bar.getWaiting() == (this.kisisayisi - bar.getEating()) && !bar.getmustWait()) {
                bar.blockRelease();
            } else {
                bar.mutexRelease();
            }
            bar.mutexAcquire();
            bar.setEating(bar.getEating() - 1);
            System.out.println("Ayrilan kisi:" + threadName);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(sushi.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(bar.getEating() + "kisi yemekte" + (sandalye - bar.getEating()) + "sandalye bosta");
            if (bar.getEating() == 0) {
                bar.setmustWait(false);
            }

            if (bar.getWaiting() == (this.kisisayisi - bar.getEating()) && !bar.getmustWait()) {
                bar.blockRelease();
            } else {
                bar.mutexRelease();
            }

        }
    }
}
