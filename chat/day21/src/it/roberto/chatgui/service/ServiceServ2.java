package it.roberto.chatgui.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.roberto.chatgui.bean.Message;
import it.roberto.chatgui.dao.MessageDao;
import it.roberto.chatgui.daoImpl.MessageDaoImpl;

public class ServiceServ2 implements Runnable {

//	private static Logger logger = Logger.getLogger(ServiceServ2.class.getName());
	private static Logger logger = LoggerFactory.getLogger(ServiceServ2.class);
	
	private static int PORT = 10002;
	
	private MessageDao messageDao = new MessageDaoImpl();

	@Override
	public void run() {
		Socket client = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		List<Message> messageList = null;
		Message mess = null;
		ServerSocket serverSocket = null;

		try {
			serverSocket = new ServerSocket(PORT);
//			log.info("Service2@"+ InetAddress.getLocalHost() + " is up.........");
			logger.info("Service2@"+ InetAddress.getLocalHost() + " is up.........");
			logger.info("Service2@"+ InetAddress.getLocalHost() + " is up.........");


			while (true) {
				try {
//					log.info("Service2 wait connection for anyone client......");
					logger.info("Service2 wait connection for anyone client......");
					
					client = serverSocket.accept();
//					log.info("Service2 connection established to CLIENT with ip: \"" + client.getInetAddress() +"\"");
					logger.info("Service2 connection established to CLIENT with ip: \"" + client.getInetAddress() +"\"");
						

					// leggo quello che il client mi invia
					in = new ObjectInputStream(client.getInputStream());
					Long readValue = (Long) in.readObject();
//					log.info("Service2 received message for CLIENT with ip: \"" + client.getInetAddress() + "\"" +
//							"\n the message received is " + readValue + "(type " + readValue.getClass().getSimpleName() +")");
					logger.info("Service2 received message for CLIENT with ip: \"" + client.getInetAddress() + "\"" +
							"\n the message received is " + readValue + "(type " + readValue.getClass().getSimpleName() +")");
					

					// invio al client una list di oggetti Message
					messageList = messageDao.findByDate(readValue);
//					log.info("Service2 execute query findByDate("+ readValue + ")\n\n takes following item:\n" + messageList.toString());
					logger.info("Service2 execute query findByDate("+ readValue + ")\n\n takes following item:\n" + messageList.toString());
					
					
					out = new ObjectOutputStream(client.getOutputStream());
					out.writeObject(messageList);
					out.flush();
//					log.info("Service2 send message to CLIENT with ip: \"" + client.getInetAddress() + "\"" +
//							"\n the message send is of type: " + messageList.getClass().getSimpleName());
					logger.info("Service2 send message to CLIENT with ip: \"" + client.getInetAddress() + "\"" +
							"\n the message send is of type: " + messageList.getClass().getSimpleName());
				} catch (IOException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} finally {
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
				}

			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}