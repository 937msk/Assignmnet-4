package assigm;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class Arrayblockingqueue {
	    public static void main(String[] args) {
	        final int CAPACITY = 5;
	        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(CAPACITY);

	        Thread producer = new Thread(() -> {
	            int value = 0;
	            try {
	                while (true) {
	                    queue.put(value);
	                    System.out.println("Produced: " + value);
	                    value++;
	                    Thread.sleep(700);
	                }
	            } catch (InterruptedException ex) {
	                Thread.currentThread().interrupt();
	            }
	        });

	        Thread consumer = new Thread(() -> {
	            try {
	                while (true) {
	                    int value = queue.take();
	                    System.out.println("Consumed: " + value);
	                    Thread.sleep(1000);
	                }
	            } catch (InterruptedException ex) {
	                Thread.currentThread().interrupt();
	            }
	        });

	        producer.start();
	        consumer.start();
	    }
	}
