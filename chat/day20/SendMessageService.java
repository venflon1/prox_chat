package roberto.day20.chat;

public class SendMessageService {
	
	public static void main(String[] args) {
		ServiceClient1 serviceClient1 = new ServiceClient1();
//		ServiceClient2 serviceClient2 = new ServiceClient2();
		
		Thread thread1 = new Thread(serviceClient1);
//		Thread thread2 = new Thread(serviceClient2);
		
		thread1.start();
//		thread2.start();
	}
}
