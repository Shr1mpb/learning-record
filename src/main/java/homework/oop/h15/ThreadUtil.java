package homework.oop.h15;

public class ThreadUtil {
    private StringBuffer sb;

    public ThreadUtil(StringBuffer sb) {
        this.sb = sb;
    }

    public void start() {
        new Thread(()->{
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            sb.append("ok");
        }).start();
    }

}
