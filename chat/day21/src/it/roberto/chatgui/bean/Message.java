package it.roberto.chatgui.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Message implements Serializable{
	
	private long id;
	private String userName;
	private String textMessage;
	private LocalDateTime mexTime ;
	
	/**
	 * 
	 */
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Message(long id, String userName, String textMessage, LocalDateTime mexTime) {
		this(userName, textMessage, mexTime);
		this.id = id;
	}
	/**
	 * @param userName
	 * @param textMessage
	 * @param mexTime
	 */
	public Message(String userName, String textMessage, LocalDateTime mexTime) {
		super();
		this.userName = userName;
		this.textMessage = textMessage;
		this.mexTime = mexTime;
	}
	
	
	/**
	 * @return the id
	 * */
	public long getId() {
		return id;
	}

	/**
	 * @param username id that we want set
	 * */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the textMessage
	 */
	public String getTextMessage() {
		return textMessage;
	}

	/**
	 * @param textMessage the textMessage to set
	 */
	public void setTextMessage(String textMessage) {
		this.textMessage = textMessage;
	}

	/**
	 * @return the mexTime
	 */
	public LocalDateTime getMexTime() {
		return mexTime;
	}

	/**
	 * @param mexTime the mexTime to set
	 */
	public void setMexTime(LocalDateTime mexTime) {
		this.mexTime = mexTime;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", userName=" + userName + ", textMessage=" + textMessage + ", mexTime=" + mexTime
				+ "]";
	}
}
