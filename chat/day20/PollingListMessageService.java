package roberto.day20.chat;

public class PollingListMessageService {

	public static void main(String[] args) {
		ServiceClient2 serviceClient2 = new ServiceClient2();
		Thread thread2 = new Thread(serviceClient2);
		thread2.start();
	}

}
