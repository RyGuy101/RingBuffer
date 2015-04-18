import java.util.Random;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		RingBuffer rb = new RingBuffer(9);
		Consumer c = new Consumer(rb);
		Producer p = new Producer(rb);
		Thread.sleep(10000);
		p.kill();
		p.join();
		c.join();
	}
}
