package roberto.day16;

public class MultiThreadClient {
	
	public static void main(String[] args) {
		ServiceClient1 serviceClient1 = new ServiceClient1();
		
		Thread thread1 = new Thread(serviceClient1);
		
		thread1.start();
	}
}
