package it.roberto.chatgui.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.roberto.chatgui.bean.Message;
import it.roberto.chatgui.dao.MessageDao;
import it.roberto.chatgui.daoImpl.MessageDaoImpl;

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
					Message mess = (Message) is.readObject();
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