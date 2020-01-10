USE chat_db;
CREATE TABLE MESSAGE(
	id int AUTO_INCREMENT,
	message_date varchar(50) NOT NULL,
	username varchar(55) NOT NULL,
	text varchar(255) NOT NULL,
	
	PRIMARY KEY(id)
);