public class Main {
    static final Object lock = new Object();
    static Boolean isEvenTrue = true;

    public static void main(String[] args) {
        Thread evenThread;
        Thread oddThread;

        evenThread = new Thread(new Runnable() { //чётные
            @Override
            public void run() {
                synchronized (lock) { // не заходим в это поле, плка выполняется другой поток
                    lock.notify();
                }
            }
        });
        oddThread = new Thread(new Runnable() { //не чётные
            @Override
            public void run() {
                synchronized (lock) {
                    if(evenThread.isAlive()) System.out.println("Ура");
                    else System.out.println("Эхх");
                    lock.notify();
                }
            }
        });


        evenThread.start(); //через run() поток в главном потоке
        oddThread.start();
    }
}