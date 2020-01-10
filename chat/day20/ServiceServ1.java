package roberto.day20.chat;

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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Roberto
 */
public class ServiceServ1 implements Runnable {

//	private static Logger logger = Logger.getLogger(ServiceServ1.class.getName());
	private static Logger logger = LoggerFactory.getLogger(ServiceServ1.class);

	private static int PORT = 10001;
	
	private MessageDao messageDao = new MessageDaoImpl();

	@Override
	public void run() {
		Socket clientSocket = null;
		
		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
//			log.info("Service1@"+ InetAddress.getLocalHost() + " is up.........");
			logger.info("Service1@"+ InetAddress.getLocalHost() + " is up.........");

			while (true) {
				clientSocket = serverSocket.accept();
//				log.info("Service1 accepted connection to ip: \"" + clientSocket.getInetAddress() + "\".......");
				logger.info("Service1 accepted connection to ip: \"" + clientSocket.getInetAddress() + "\".......");

				ObjectInputStream is = null;
				try {
					is = new ObjectInputStream(clientSocket.getInputStream());
					exercises.day13.chat.Message mess = (exercises.day13.chat.Message) is.readObject();
//					log.info("Service1 received message: " + "[" + mess + "]......");
					logger.info("Service1 received message: " + "[" + mess + "]......");
					
					boolean ret = messageDao.saveMessage(mess);
//					log.info("Service1 saved massage into DB? " + ret);
					logger.info("Service1 saved massage into DB? " + ret);
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
					e.printStackTrace();
				}
		}

	}

}