public class Main {
    private volatile Boolean flag = false; //поток получает актуальное значение, а не сохраняет в кэш

    Runnable gui = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Stop gui");
            flag = true;
        }
    };
    Runnable logic = new Runnable() {
        @Override
        public void run() {
            while (!flag){

            }
            System.out.println("Stop logic");
        }
    };

     void  doStart(){
        new Thread(gui).start();
        System.out.println("Start gui");
        new Thread(logic).start();
        System.out.println("Start logic");
    }

    public static void main(String[] args) {
        new Main().doStart();
    }

}