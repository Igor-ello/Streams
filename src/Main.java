public class Main {
    private static int MAX_VALUE = 20;
    static final Object lock = new Object();
    static Boolean isEvenTrue = true;

    public static void main(String[] args) {
        Thread evenThread = new Thread(new Runnable() { //чётные
            @Override
            public void run() {
                for (int i = 2; i < MAX_VALUE; i++){
                    synchronized (lock) { // не заходим в это поле, плка выполняется другой поток
                        while (!isEvenTrue) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("Первый поток " + i);
                        isEvenTrue = false;
                        lock.notify();
                    }
                }
            }
        });
        Thread oddThread = new Thread(new Runnable() { //не чётные
            @Override
            public void run() {
                for (int i = 1; i < MAX_VALUE; i++){
                    synchronized (lock) {
                        while (isEvenTrue) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        }
                        System.out.println("Второй поток " + i);
                        isEvenTrue = true;
                        lock.notify();
                    }
                }
            }
        });


        evenThread.start(); //через run() поток в главном потоке
        oddThread.start();
    }
}