package it.roberto.chatgui.controller;

import it.roberto.chatgui.service.ServiceServ1;
import it.roberto.chatgui.service.ServiceServ2;

public class MultiThreadServer {
	
	private ServiceServ1 service1 = new ServiceServ1();
	private ServiceServ2 service2 = new ServiceServ2();
	
	public MultiThreadServer() {
		Thread thread1 = new Thread(service1);
		Thread thread2 = new Thread(service2);
		
		thread1.start();
		thread2.start();
	}
}
