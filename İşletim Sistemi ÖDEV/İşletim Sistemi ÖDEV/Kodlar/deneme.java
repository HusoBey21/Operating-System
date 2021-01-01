
public class deneme {
    public static void main(String[] args) throws InterruptedException {

        sushibar ay = new sushibar();
        Thread[] a = new Thread[8];
        for (int i = 0; i < a.length; i++) {
            a[i] = new Thread(new sushi("Thread:" + String.valueOf(i), ay, i));
            a[i].start();
        }

    }

}
