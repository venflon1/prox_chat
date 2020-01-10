package it.roberto.chatgui.controller;

import it.roberto.chatgui.bean.Message;
import it.roberto.chatgui.service.ServiceClient1;

public class SendMessageService {
	
	private ServiceClient1 serviceClient1 = new ServiceClient1();
	
	public SendMessageService() {
		Thread thread1 = new Thread(serviceClient1);
		thread1.start();
	}
	
	public void sendMessage(Message message) {
		
	}
}
