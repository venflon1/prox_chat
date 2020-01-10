package roberto.day16;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.logging.Logger;

/**
 * @author Roberto
 */
public class ServiceServ1 implements Runnable {

	private static Logger log = Logger.getLogger(ServiceServ1.class.getName());
	
	private MessageDao messageDao = new MessageDaoImpl();

	@Override
	public void run() {
		Socket clientSocket = null;
		
		try {
			ServerSocket serverSocket = new ServerSocket(9999);
//			log.info("SERVER-CHAT_1.0@"+ InetAddress.getLocalHost() + " is up.........");

			while (true) {
				clientSocket = serverSocket.accept();
//				log.info("server accepted connection to " + clientSocket.getInetAddress() + ".......");

				ObjectInputStream is = null;
				try {
					is = new ObjectInputStream(clientSocket.getInputStream());
					exercises.day13.chat.Message mess = (exercises.day13.chat.Message) is.readObject();
//					log.info("received message: " + "[" + mess + "]......");
					
					System.out.println(messageDao.saveMessage(mess));
//					log.info("store message to DB......");
				} catch (IOException e) {
					e.printStackTrace();
				}
			} // end while
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(clientSocket != null)
				try {
					clientSocket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}