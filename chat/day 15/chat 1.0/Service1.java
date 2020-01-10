package roberto.day15.database.chat;

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
public class Service1 implements Runnable {

	private static Logger log = Logger.getLogger(Service1.class.getName());
	
	private MessageDao messageDao = new MessageDaoImpl();

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(10001)) {


			while (true) {
				log.info("SERVER-CHAT_1.0@"+ InetAddress.getLocalHost() + " is up.........");
				Socket clientSocket = serverSocket.accept();
				log.info("server accepted connection to " + clientSocket.getInetAddress() + ".......");

				try (ObjectInputStream oos = new ObjectInputStream(clientSocket.getInputStream())) {
					exercises.day13.chat.Message mess = (exercises.day13.chat.Message) oos.readObject();
					log.info("received message: " + "[" + mess + "]......");
					messageDao.saveMessage(mess);
					log.info("store message to DB......");
				}
			} // end while
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}