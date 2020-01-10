package roberto.day16;

import java.time.LocalDateTime;
import java.util.List;

import exercises.day13.chat.Message;
import roberto.day13.ListMessageChat;


public class TestJDBC2 {
	public static void main(String[] args) {
		MessageDao messageDao = new MessageDaoImpl();
		
		Message msg = new Message();
		msg.setUserName("paolo.perna");
		msg.setTextMessage("soleee!");
		msg.setMexTime(LocalDateTime.now());
		
//		System.out.println("All messages:");
//		messageDao.findAll().forEach( m -> System.out.println(m));
		
//		System.out.println("saved = " + messageDao.saveMessage(msg));
//		System.out.println("findBy id 4"  + messageDao.findById(4L));
//		System.out.println("deleted = " + messageDao.deleteMessage(msg));
		
//		List<Message> list = messageDao.findByDate(99999999999L);
//		list.forEach( message -> System.out.println(message));
		List<Message> list = messageDao.findAllOrderMessageByDate(false);
		list.forEach( message -> System.out.println(message));
		
	}
}
