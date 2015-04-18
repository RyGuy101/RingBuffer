import java.util.Random;

public class Consumer extends Thread {
	RingBuffer rb;
	Random random = new Random();

	public Consumer(RingBuffer rb) {
		this.rb = rb;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		boolean done = false;
		while (!done) {
			try {
				long start = System.currentTimeMillis();
				if (rb.removeNumber() == -1) {
					done = true;
				}
				long time = System.currentTimeMillis() - start;
				try {
					Thread.sleep(random.nextInt(200) + 1 - time);
				} catch (Exception e) {
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
