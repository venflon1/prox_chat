package roberto.day20.chat;

import roberto.day15.database.chat.Service1;
import roberto.day15.database.chat.Service2;

public class MultiThreadServer {
	
	public static void main(String[] args) {
		ServiceServ1 service1 = new ServiceServ1();
		ServiceServ2 service2 = new ServiceServ2();
		
		Thread thread1 = new Thread(service1);
		Thread thread2 = new Thread(service2);
		
		thread1.start();
		thread2.start();
	}
}
