package roberto.day15.database.chat;

public class MultiThreadServer {
	
	public static void main(String[] args) {
		Service1 service1 = new Service1();
		Service2 service2 = new Service2();
		
		Thread thread1 = new Thread(service1);
		Thread thread2 = new Thread(service2);
		
		thread1.start();
		thread2.start();
	}
}
