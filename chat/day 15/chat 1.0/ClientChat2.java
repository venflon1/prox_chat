package roberto.day15.database.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class ClientChat2 {

	private static Logger log = Logger.getLogger(ClientChat2.class.getName());

	public static void main(String[] args) {
		try {
//			String hostJesus = "JESUS";
			Socket client = new Socket(InetAddress.getLocalHost(), 10002);
//			Socket client = new Socket(hostJesus, 10002);

			log.info("CLIENT-CHAT 2.0 is up and connected to SERVER-CHAT 2.0.........");
			ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());


			// invio un long al server che e' in ascolto sulla porta 9090
			@SuppressWarnings("deprecation")
			Long time = new Long(9828L);
			out.writeObject(time);
			out.flush();
			log.info("CLIENT-CHAT 2.0 send long object to server.......");

			// client riceve dal server una lista di oggetti di tipo
			// exercises.day13.chat.Message
			ObjectInputStream in = new ObjectInputStream(client.getInputStream());
			ArrayList<exercises.day13.chat.Message> listMess = (ArrayList<exercises.day13.chat.Message>) in
					.readObject();
			log.info("CLIENT-CHAT 2.0 received exercises.day13.chat.Message by server.......");
			log.info("List messages:");
			
			listMess.forEach((mess) -> System.out.println("\t" + mess));
			log.info("CLIENT-CHAT 2.0 close.......");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
