package roberto.day15.database.chat;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class LoadConfigDB {

	private Properties props;
	
	public LoadConfigDB() {
		super();
		this.props = new Properties();
	}
	
	public void loadProps()  {
		File file = new File("C:\\Users\\titano\\eclipse-workspace\\java.base\\trunk\\src\\roberto\\day15\\database\\chat\\application.properties");
		if(!file.exists())
			System.out.println("file properties not exist!");
		
		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			this.props.load(fis);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Properties getProperties() {
		return this.props;
	}
}
