package roberto.day20.chat;

import java.time.LocalDateTime;
import java.util.List;

import exercises.day13.chat.Message;
public interface MessageDao {

	public List<Message>findAll();
	
	public List<Message> findAllOrderMessageByDate(boolean asc);
	
	public Message findById(long id);
	
	public List<Message> findByUsername(String username);
	
	public List<Message> findByDate(LocalDateTime date);
	
	public List<Message> findByDate(Long time);
	
	public boolean saveMessage(Message message);
		
	public boolean deleteMessage(Message message);

}
