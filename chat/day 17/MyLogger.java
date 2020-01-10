package roberto.day16;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MyLogger {

	private Logger logger = null;
	
	private static int LIMIT = 30*1024;	// 30 MB limit file log
	
	public MyLogger(String className) throws IOException {
		this.logger = Logger.getLogger(className);
		FileHandler fileHandler = new FileHandler(".\\src\\roberto\\day16\\" + className + ".log", LIMIT, 1, true);
		this.logger.addHandler(fileHandler);
	}
	
	public static MyLogger getLogger(String className) {
		try {
			return new MyLogger(className);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void info(String msg) {
		this.logger.info(msg);
	}
	
	public void severe(String msg) {
		this.logger.severe(msg);
	}
	
	public void warning(String msg) {
		this.logger.warning(msg);
	}
}
