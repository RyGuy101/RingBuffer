import java.util.Random;

public class Producer extends Thread {
	RingBuffer rb;
	boolean done = false;
	Random random = new Random();

	public Producer(RingBuffer rb) {
		this.rb = rb;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		while (!done) {
			try {
				long start = System.currentTimeMillis();
				rb.addNumber(random.nextInt(100) + 1);
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

	public void kill() throws InterruptedException {
		done = true;
		rb.addNumber(-1);
	}
}
