package assigm;
import java.util.LinkedList;
import java.util.Queue;

	public class Producerconsumer {

	    private static final int CAPACITY = 5;
	    private final Queue<Integer> buffer = new LinkedList<>();

	    public static void main(String[] args) {
	    	Producerconsumer pc = new Producerconsumer();
	        new Thread(pc.new Producer()).start();
	        new Thread(pc.new Consumer()).start();
	    }

	    class Producer implements Runnable {
	        public void run() {
	            int value = 0;
	            try {
	                while (true) {
	                    synchronized (buffer) {
	                        while (buffer.size() == CAPACITY) {
	                            buffer.wait(); 
	                        }
	                        System.out.println("Produced: " + value);
	                        buffer.add(value++);
	                        buffer.notify(); 
	                    }
	                    Thread.sleep(1000); 
	                }
	            } catch (InterruptedException ex) {
	                Thread.currentThread().interrupt();
	            }
	        }
	    }

	    class Consumer implements Runnable {
	        @Override
	        public void run() {
	            try {
	                while (true) {
	                    synchronized (buffer) {
	                        while (buffer.isEmpty()) {
	                            buffer.wait(); 
	                        }
	                        int value = buffer.poll();
	                        System.out.println("Consumed: " + value);
	                        buffer.notify();
	                    }
	                    Thread.sleep(100);
	                }
	            } catch (InterruptedException ex) {
	                Thread.currentThread().interrupt();
	            }
	        }
	    }
	}

