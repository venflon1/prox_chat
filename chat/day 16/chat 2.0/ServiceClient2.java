package roberto.day16;

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
import java.util.logging.Logger;

import exercises.day13.chat.Message;
import roberto.day13.ListMessageChat;

public class ServiceClient2 implements Runnable{

	private static Logger log = Logger.getLogger(ServiceClient2.class.getName());
	
	private Long lastMessageRead = 0L;

	@Override
	public void run() {
		Socket client = null;
		
		try {
			ObjectOutputStream out = null;
			try {
				while (true) {
					client = new Socket(InetAddress.getLocalHost(), 8888);
					out = new ObjectOutputStream(client.getOutputStream());
	
					// invio un long al server che e' in ascolto sulla porta 10002
					
					out.writeObject(this.lastMessageRead);
	//			log.info("CLIENT-CHAT 2.0 send long object to server.......");
	
					// client riceve dal server una lista di oggetti di tipo
					// exercises.day13.chat.Message
					ObjectInputStream in = new ObjectInputStream(client.getInputStream());
					ArrayList<Message> listMess = (ArrayList<Message>) in.readObject();
					log.info("numero messaggi non letti " + listMess.size());
	//			log.info("CLIENT-CHAT 2.0 received exercises.day13.chat.Message by server.......");
	//			log.info("List messages:");
	
					if(listMess.size() > 0) {						
						listMess.forEach((mess) -> System.out.println("\t -> " + mess.getMexTime() + " " + mess.getUserName() + " " + mess.getTextMessage()));
						Collections.sort(listMess, new Comparator<Message>() {

							@Override
							public int compare(Message o1, Message o2) {
								String dateM1, dateM2;
								dateM1 = Util.localDateTime2String(o1.getMexTime());
								dateM2 = Util.localDateTime2String(o2.getMexTime());
								
								return dateM1.compareTo(dateM2);
							}
							
						});
						Message lastMessage = listMess.get(listMess.size()-1);
						System.out.println("lastMessage = " + lastMessage);
						
						// update lastMessageRead se c'è almeno un messaggio da leggere  
						this.lastMessageRead = Util.localDateTimeWithZone2long(lastMessage.getMexTime());
					}
					
					// sleep per 6 s.
					Thread.sleep(10*1000);
	//			log.info("CLIENT-CHAT 2.0 close.......");
				} // end while
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			} catch(InterruptedException ex) {
				ex.printStackTrace();
			}finally {
				if(out != null)
					out.close();
			}
		
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} finally {
			if(client != null)
				try {
					client.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}
	
	
}
