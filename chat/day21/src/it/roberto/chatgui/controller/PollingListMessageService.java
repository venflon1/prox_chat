package it.roberto.chatgui.controller;

import java.util.List;

import it.roberto.chatgui.bean.Message;
import it.roberto.chatgui.service.ServiceClient2;

public class PollingListMessageService {
	
	private ServiceClient2 serviceClient2 = new ServiceClient2();

	public PollingListMessageService() {
		super();
		this.startPolling();
	}
	
	private  void startPolling() {
		Thread thread2 = new Thread(serviceClient2);
		thread2.start();
	}
	
	public List<Message> getListMessage(){
		return serviceClient2.getListMessage();
	}
	
}
