import java.util.Random;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		int capcity = 9;
		RingBuffer rb = new RingBuffer(9);
		Random random = new Random();
		for (int i = 0; i < capcity / 2; i++) {
			rb.addNumber(random.nextInt(100) + 1);
		}
		Consumer c = new Consumer(rb);
		Producer p = new Producer(rb);
		Thread.sleep(10000);
		p.kill();
		p.join();
		c.join();
	}
}
