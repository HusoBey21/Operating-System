import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class sushibar {
    int eating;
    int waiting;
    private Semaphore mutex; // Karşılıklı dışlamayı önleme amaçlı semafor oluşturduk.
    private Semaphore block;
    private boolean mustWait = false;

    public sushibar() {
        this.mutex = new Semaphore(1, true);
        this.block = new Semaphore(0, true);
        eating = 0;
        waiting = 0;

    }

    public void mutexAcquire() {
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(sushibar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void blockAcquire() {
        try {
            block.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(sushibar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void mutexRelease() {
        mutex.release();
    }

    public void blockRelease() {
        block.release();
    }

    public void setmustWait(boolean mustwait) {
        mustWait = mustwait;
    }

    public boolean getmustWait() {
        return mustWait;
    }

    public void setEating(int val) {
        this.eating = val;
    }

    public int getEating() {
        return eating;
    }

    public void setWaiting(int val) {
        this.waiting = val;
    }

    public int getWaiting() {
        return waiting;
    }

}