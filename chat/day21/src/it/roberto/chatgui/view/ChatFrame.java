package it.roberto.chatgui.view;

import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JFrame;

public class ChatFrame extends JFrame{

	private Container container;
	private Dimension dimension;
	
	public ChatFrame(String frameName, int width, int height) {
		super(frameName);
		this.container = this.getContentPane();
		this.dimension = new Dimension(width, height);
	}
 
	public static void main(String[] args) {
	}
}
