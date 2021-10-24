import java.util.concurrent.Semaphore;

public class Tunnel extends Stage {
    private Semaphore cmp;
    public Tunnel() {
        cmp = new Semaphore(MainClass.CARS_COUNT/2); //семафор на половину участников
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            if (!cmp.tryAcquire()) {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                cmp.acquire();
            }
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
           cmp.release();
            }

    }
}
