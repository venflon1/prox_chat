package it.roberto.chatgui.view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import it.roberto.chatgui.bean.Message;


public class MainFrame extends JFrame {

	private Container container;
	private Dimension dimension;
	private LayoutManager layout;
	private JMenuBar menuBar;
	private JMenu menu;
	private List<JMenuItem> listMenuItem;
	private JTextArea textArea;
	private JScrollPane textAreaScroll;
	private JButton button;

	public MainFrame(String frameName, int width, int height) {
		super(frameName);
		this.container = this.getContentPane();
		this.dimension = new Dimension(width, height);
		
		this.init();
		this.createGUI();
	}
	

	private void init() {
		this.setSize(this.dimension);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setResizable(false);
		this.setVisible(true);
	}
	
	private void createGUI() {
		this.createMenu(new JMenuItem("File"), new JMenuItem("File"));
		this.createScrollText();	
		this.createButton();
		this.setLayout();
	}

	private void setLayout() {
		this.layout = new BorderLayout();
		this.container.setLayout(this.layout);
		this.container.add(this.menuBar, BorderLayout.NORTH);
		this.container.add(this.textAreaScroll, BorderLayout.CENTER);
		this.container.add(this.button, BorderLayout.SOUTH);

	}

	private void createMenu(JMenuItem...  menuItems) {
		this.listMenuItem = new ArrayList<JMenuItem>();
		for(int i=0; i<menuItems.length; i++) {
			this.listMenuItem.add(menuItems[i]);
		}
		
		setMenu();
		setMenuItem(this.listMenuItem);
		setMenuBar();
		
	}
	
	private void setMenuBar() {
		this.menuBar = new JMenuBar();
		this.menuBar.add(this.menu);
	}

	private void setMenu() {
		this.menu = new JMenu();
		this.menu.addSeparator();
	}

	private void setMenuItem(List<JMenuItem> listMenuItems) {		
		this.listMenuItem.forEach( menuItem -> this.menu.add(menuItem));
	}
	
	private void setTextArea() {
		this.textArea = new JTextArea();
		this.textArea.setEditable(false);
	}
	
	private void createScrollText() {
		setTextArea();
		this.textAreaScroll = new JScrollPane(this.textArea);
	}
	
	private void createButton() {
		this.button = new JButton("send message");
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame("chat 3.0", 500, 500);
	}
	
	public void displayMessages(List<Message> list) {
			this.textArea.setText("");
			list.forEach( message -> this.textArea.append("->" + message + "\n"));
	}
}
