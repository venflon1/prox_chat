package roberto.day16;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;

import exercises.day13.chat.Message;

public class ServiceClient1 implements Runnable {

	private static Logger log = Logger.getLogger(ServiceClient1.class.getName());

	@Override
	public void run() {
		Scanner stdIn = new Scanner(System.in);
		Socket socketClient = null;
		try {
			while (true) {
				socketClient = new Socket(InetAddress.getLocalHost(), 9999);
//				log.info("CLIENT-CHAT 1.0 is up and connected to SERVER-CHAT 1.0.........");
			
				System.out.print("insert text-message\n> ");
				String msgText = stdIn.nextLine();
				
				ObjectOutputStream os = null;
				try {
					os = new ObjectOutputStream(socketClient.getOutputStream());
					Message msg = new Message();
					msg.setUserName("roberto.amato");
					msg.setTextMessage(msgText);
					msg.setMexTime(LocalDateTime.now());
					os.writeObject(msg);
//					log.info("CLIENT-CHAT 1.0 close.......");
					System.out.println(msg + " inviato...");
				} catch (IOException e1) {
					e1.printStackTrace();
				} finally {
					if (os != null)
						os.close();
				}
			}
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if(socketClient != null)
				try {
					socketClient.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
}