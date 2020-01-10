package it.roberto.chatgui.app;

import java.util.List;

import it.roberto.chatgui.bean.Message;
import it.roberto.chatgui.controller.MultiThreadServer;
import it.roberto.chatgui.controller.PollingListMessageService;
import it.roberto.chatgui.controller.SendMessageService;
import it.roberto.chatgui.view.MainFrame;

public class App {
	
	public static void main(String[] args) throws InterruptedException {
		
		MultiThreadServer server = new MultiThreadServer();
		PollingListMessageService poll = new PollingListMessageService();
//		SendMessageService sms = new SendMessageService();
		
		List<Message> listMessage = poll.getListMessage();
		
		Thread t1 = new Thread( () ->{
			MainFrame view1 =  new MainFrame("chat 3.0", 700, 700);
			while(true) {	
				view1.displayMessages(listMessage);
				try {
					Thread.sleep(3*1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		t1.start();
		
//		Thread t2 = new Thread( () ->{
//			ChatFrame view1 =  new ChatFrame("chat 3.0", 500, 500);
//		}
//		t2.start();
		
	}

}
