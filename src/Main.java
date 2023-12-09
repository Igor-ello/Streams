public class Main {
    private static int MAX_VALUE = 20;

    public static void main(String[] args) {
        Thread evenThread = new Thread(new Runnable() { //чётные
            @Override
            public void run() {
                for (int i = 2; i < MAX_VALUE; i++){
                    System.out.println("Первый поток " + i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Thread oddThread = new Thread(new Runnable() { //не чётные
            @Override
            public void run() {
                for (int i = 1; i < MAX_VALUE; i++){
                    System.out.println("Второй поток " + i);
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        evenThread.start(); //через run() поток в главном потоке
        oddThread.start();
    }
}