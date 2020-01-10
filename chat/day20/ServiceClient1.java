package roberto.day20.chat;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exercises.day13.chat.Message;

public class ServiceClient1 implements Runnable {

//	private static Logger log = Logger.getLogger(ServiceClient1.class.getName());
	private static Logger logger = LoggerFactory.getLogger(ServiceClient1.class);

	@Override
	public void run() {
		Scanner stdIn = new Scanner(System.in);
		Socket socketClient = null;
		
		try {
			while (true) {				
				System.out.print("insert text-message\n> ");
				String msgText = stdIn.nextLine();
				
				socketClient = new Socket(InetAddress.getLocalHost(), 10001);
//				log.info("Client1 is up and connected to Server whit ip: \"" + socketClient.getInetAddress() + "\"");
				logger.info("Client1 is up and connected to Server whit ip: \"" + socketClient.getInetAddress() + "\"");
				
				ObjectOutputStream os = null;
				try {
					os = new ObjectOutputStream(socketClient.getOutputStream());
					Message msg = new Message();
					msg.setUserName("roberto.amato");
					msg.setTextMessage(msgText);
					msg.setMexTime(LocalDateTime.now());
					os.writeObject(msg);
//					log.info("Client1 send message to Server1 with ip: \"" + socketClient.getInetAddress() + "\"\n" +
//							"message sended type of " + msg.getClass().getSimpleName());
					logger.info("Client1 send message to Server1 with ip: \"" + socketClient.getInetAddress() + "\"\n" +
							"message sended type of " + msg.getClass().getSimpleName());
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
					e.printStackTrace();
				}
		}
	}
}