package roberto.day16;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import java.sql.PreparedStatement;

import exercises.day13.chat.Message;

public class MessageDaoImpl implements MessageDao {

	private DBManager dbManager = DBManager.getInstance();

	@Override
	public List<Message> findAll() {

		Connection connection = null;
		List<Message> listMessages = null;
		Statement stmt = null;

		try {
			connection = this.dbManager.getMySqlConnection();

			stmt = connection.createStatement();
			String SQL = "SELECT * FROM MESSAGE";
			ResultSet resultSet = stmt.executeQuery(SQL);

			listMessages = new ArrayList<>();
			while (resultSet.next()) {
				Message m = new Message();
				m.setId(resultSet.getLong("id"));
				m.setUserName(resultSet.getString("username"));
				m.setTextMessage(resultSet.getString("text"));
				String s = resultSet.getString("message_date");
				String ss = s.substring(0, s.length()-2);
				m.setMexTime(Util.String2LocalDateTime(ss));
				listMessages.add(m);
			}
			
			stmt.close();
			connection.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return listMessages;
	}

	@Override
	public Message findById(long id) {
		Connection connection = null;
		Message messageToRetrieve = null;
		PreparedStatement stmt = null;
		
		try {			
			connection = this.dbManager.getMySqlConnection();
			String SQL = "SELECT * FROM MESSAGE WHERE MESSAGE.id = ?";
			stmt = connection.prepareStatement(SQL);
			stmt.setLong(1, id);
			
			ResultSet result = stmt.executeQuery();
			if(result.next()) {
				messageToRetrieve = new Message();
				messageToRetrieve.setId(result.getLong("id"));
				messageToRetrieve.setUserName(result.getString("username"));
				messageToRetrieve.setTextMessage(result.getString("text"));
				
				String s = result.getString("message_date");
				String ss = s.substring(0, s.length()-2);
				messageToRetrieve.setMexTime(Util.String2LocalDateTime(ss));
			}
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return messageToRetrieve;
	}

	@Override
	public List<Message> findByUsername(String username) {
		// TO-DO
		return null;
	}

	@Override
	public List<Message> findByDate(LocalDateTime date) {
		// TO-DO
		return null;
	}

	@Override
	public List<Message> findByDate(Long time) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		List<Message> listMessages = new ArrayList<Message>();
		String dateTime = Util.localDateTime2String(Util.long2LocalDateTime(time));
		
		try {
			connection = dbManager.getMySqlConnection();
			String SQL = "SELECT * FROM MESSAGE WHERE MESSAGE.message_date > ?";
			stmt = connection.prepareStatement(SQL);
			stmt.setString(1, dateTime);
			
			ResultSet resultSet = stmt.executeQuery();
			Message m = null;
			while(resultSet.next()){
				m = new Message();
				m.setId(resultSet.getLong("id"));
				m.setUserName(resultSet.getString("username"));
				m.setTextMessage(resultSet.getString("text"));
				
				String s = resultSet.getString("message_date");
				String ss = s.substring(0, s.length()-2);
				m.setMexTime(Util.String2LocalDateTime(ss));
				
				listMessages.add(m);
			}
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return listMessages;
	}
	
	@Override
	public boolean saveMessage(Message message) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		boolean saved = false;
		
		try {
			connection = this.dbManager.getMySqlConnection();
			String SQL = "INSERT INTO MESSAGE(username, text, message_date)"+ 
						 "VALUES(?, ?, ?)";
			stmt = (PreparedStatement) connection.prepareStatement(SQL);
			stmt.setString(1, message.getUserName());
			stmt.setString(2, message.getTextMessage());
			
			String messageDate = Util.localDateTime2String(message.getMexTime());
			stmt.setString(3, messageDate);

			if(stmt.executeUpdate() != 0)
				saved = true;
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return saved;
	}

	@Override
	public boolean deleteMessage(Message message) {
		
		Connection connection = null;
		PreparedStatement stmt = null;
		long idMessageToDelete = message.getId();
		boolean deleted = false;
		
		try {
			connection = this.dbManager.getMySqlConnection();
			Message messageToDelete = this.findById(idMessageToDelete);
			if(messageToDelete != null) {
//				connection.setAutoCommit(false);
//				connection.beginRequest();
				String SQL = "DELETE FROM MESSAGE WHERE MESSAGE.id = ?";
				stmt = connection.prepareStatement(SQL);
				stmt.setLong(1, idMessageToDelete);
				if(stmt.executeUpdate() != 0) 
					deleted=true;
//				connection.commit();
			}
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			if(connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return deleted;
	}
	
	public List<Message> findAllOrderMessageByDate(boolean asc){
		
		Connection connection = null;
		Statement stmt = null;
		List<Message> listMessages = null;
		
		try {
			connection = this.dbManager.getMySqlConnection();
			String SQL = "SELECT * FROM MESSAGE ORDER BY MESSAGE.message_date ";
			if(asc)
				SQL += "ASC";
			else	
				SQL += "DESC";
			stmt = connection.createStatement();
			ResultSet resultSet = stmt.executeQuery(SQL);
			
			listMessages = new ArrayList<>();
			while(resultSet.next()) {
				Message m = new Message();
				m.setId(resultSet.getLong("id"));
				m.setUserName(resultSet.getString("username"));
				m.setTextMessage(resultSet.getString("text"));
				
				String s = resultSet.getString("message_date");
				String ss = s.substring(0, s.length()-2);
				m.setMexTime(Util.String2LocalDateTime(ss));

				listMessages.add(m);
			}
			
			stmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		return listMessages;
		
	}

}