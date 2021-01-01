public class denemeiki {
    public static void main(String[] args) {
        sushibar t = new sushibar();
        Thread[] k = new Thread[10];
        for (int z = 0; z < k.length; z++) {
            k[z] = new Thread(new sushiiki("Thread:" + String.valueOf(z), t, z));
            k[z].start();
        }
    }

}
