package roberto.day20.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import exercises.day13.chat.Message;
import roberto.day13.ListMessageChat;

public class ServiceClient2 implements Runnable {

//	private static Logger log = Logger.getLogger(ServiceClient2.class.getName());
	private static Logger logger = LoggerFactory.getLogger(ServiceClient2.class);

	private Long lastMessageRead = 0L;

	@Override
	public void run() {
		Socket client = null;

		try {
			ObjectOutputStream out = null;
			try {
				while (true) {
					client = new Socket(InetAddress.getLocalHost(), 10002);
					out = new ObjectOutputStream(client.getOutputStream());

					// invio un long al server che e' in ascolto sulla porta 10002
					out.writeObject(this.lastMessageRead);
//					log.info("Client2 send message to Server2 with ip: \"" + client.getInetAddress() + "\"\n" +
//							"message sended is: " + this.lastMessageRead + ", of type " + this.lastMessageRead.getClass().getSimpleName());
					logger.info("Client2 send message to Server2 with ip: \"" + client.getInetAddress() + "\"\n"
							+ "message sended is: " + this.lastMessageRead + ", of type "
							+ this.lastMessageRead.getClass().getSimpleName());

					// client riceve dal server una lista di oggetti di tipo
					// exercises.day13.chat.Message
					ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ArrayList<Message> listMess = (ArrayList<Message>) in.readObject();
//					log.info("Client2 received message by Server2 with ip: \"" + client.getInetAddress() + "\"\n" + 
//							"message received is the following:\n\n" + listMess.toString());
					logger.info("Client2 received message by Server2 with ip: \"" + client.getInetAddress() + "\"\n"
							+ "message received is the following:\n\n" + listMess.toString());

					if (listMess.size() > 0) {
						for (int i = 0; i < 30; i++) {
							System.out.print('\n');
						}
						listMess.forEach((mess) -> System.out.println(
								"\t -> " + mess.getMexTime() + " " + mess.getUserName() + " " + mess.getTextMessage()));
						Collections.sort(listMess, new Comparator<Message>() {

							@Override
							public int compare(Message o1, Message o2) {
								String dateM1, dateM2;
								dateM1 = Util.localDateTime2String(o1.getMexTime());
								dateM2 = Util.localDateTime2String(o2.getMexTime());

								return dateM1.compareTo(dateM2);
							}

						});
						Message lastMessage = listMess.get(listMess.size() - 1);

//						log.info("lastMessage of arrayList received is: " + lastMessage);
						logger.info("lastMessage of arrayList received is: " + lastMessage);
//						log.info("instance variable lastMessageRead = " + this.lastMessageRead + "of type " + this.lastMessageRead.getClass().getSimpleName());
						logger.info("instance variable lastMessageRead = " + this.lastMessageRead + "of type "
								+ this.lastMessageRead.getClass().getSimpleName());
						// update lastMessageRead se c'è almeno un messaggio da leggere
						this.lastMessageRead = Util.localDateTimeWithZone2long(lastMessage.getMexTime());
					}

					// sleep per 7 s.
					Thread.sleep(7 * 1000);
					// log.info("Client2 close.......");
				} // end while
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				if (out != null)
					out.close();
			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if (client != null)
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}

	}

}
