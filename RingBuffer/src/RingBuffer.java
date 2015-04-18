public class RingBuffer {
	private int[] numbers;
	private int start = 0;
	private int end = 0;
	private boolean empty = true;
	private boolean full = false;
	private int currentSize = 0;

	public RingBuffer(int capacity) {
		numbers = new int[capacity];
	}

	public synchronized void addNumber(int number) throws InterruptedException {
		while (full) {
			wait();
		}
		numbers[end] = number;
		end = (end + 1) % numbers.length;
		if (end == start) {
			full = true;
		}
		empty = false;
		currentSize++;
		printNumbers();
		notifyAll();
	}

	public synchronized int removeNumber() throws InterruptedException {
		while (empty) {
			wait();
		}
		int removedNum = numbers[start];
		numbers[start] = 0;
		start = (start + 1) % numbers.length;
		if (start == end) {
			empty = true;
		}
		full = false;
		currentSize--;
		printNumbers();
		notifyAll();
		return removedNum;
	}

	public int getStart() {
		return start;
	}

	public int getEnd() {
		return end;
	}

	public boolean isEmpty() {
		return empty;
	}

	public boolean isFull() {
		return full;
	}

	public int currentSize() {
		return currentSize;
	}

	public void printNumbers() {
		for (int n : numbers) {
			if (n != 0) {
				System.out.print(n + ", ");
			}
		}
		System.out.println();
	}
}
