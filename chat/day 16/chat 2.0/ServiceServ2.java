package roberto.day16;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.logging.Logger;

public class ServiceServ2 implements Runnable {

	private static Logger log = Logger.getLogger(ServiceServ2.class.getName());
	
	private MessageDao messageDao = new MessageDaoImpl();

	@Override
	public void run() {
		Socket client = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		List<exercises.day13.chat.Message> messageList = null;
		exercises.day13.chat.Message mess = null;
		ServerSocket serverSocket = null;

		try {
			log.info("SERVER-CHAT_2.0@" + InetAddress.getLocalHost() + " is up.........");

			serverSocket = new ServerSocket(8888);

			while (true) {
				try {
					log.info("SERVER-CHAT 2.0 wait connection for anyone client......");
					client = serverSocket.accept();
					log.info("SERVER-CHAT 2.0 connection established to client with ip:" + client.getInetAddress()
							+ ", port:" + client.getPort());

					// leggo quello che il client mi invia
					in = new ObjectInputStream(client.getInputStream());
					Long readValue = (Long) in.readObject();
					log.info("received message for CLIENT (ip, port)=(" + client.getInetAddress() + ","
							+ client.getPort() + ")");
					System.out.println("ip address" + client.getInetAddress() + " " + readValue.longValue());
					log.info(readValue.toString());

					// invio al client una list di oggetti Message
					messageList = messageDao.findByDate(readValue);
					System.out.println(messageList);
					out = new ObjectOutputStream(client.getOutputStream());
					out.writeObject(messageList);
					out.flush();
					log.info("SERVER-CHAT 2.0 send object to CLIENT (ip, port)= (" + client.getInetAddress() + ","
							+ client.getPort() + ")");
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
					log.info("init finally");
					if (in != null)
						try {
							in.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					if (out != null)
						try {
							out.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

//					if (serverSocket != null)
//						try {
//							serverSocket.close();
//						} catch (IOException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
					log.info("end finally");
				}

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}