package roberto.day15.database.chat;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.TimeZone;

public class Util {

	public static LocalDateTime long2LocalDateTime(Long longParam) {
		return  LocalDateTime.ofInstant(Instant.ofEpochMilli(longParam), TimeZone
                															.getDefault().toZoneId());
	}
	
	public static String localDateTime2String(LocalDateTime localDateTimeParam) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm:ss");

		return localDateTimeParam.format(formatter).toString();
	}
	
	public static long localDateTime2long(LocalDateTime localDateTimeParam) {
		return 0L;
	}

	
	public static LocalDateTime String2LocalDateTime(String stringParam) {
		String[] splitDateTime = stringParam.split(" ");
		String date = splitDateTime[0];
		String time = splitDateTime[1];
		String[] dateSplit = date.split("/");
		System.out.println("d"+dateSplit[0]);
		System.out.println("m"+dateSplit[1]);
		System.out.println("y"+dateSplit[2]);

		String[] timeSplit = time.split(":");
		
		LocalDateTime aDateTime = LocalDateTime.of(Integer.parseInt(dateSplit[0]), Month.of(Integer.parseInt(dateSplit[1])),Integer.parseInt(dateSplit[2]),
																Integer.parseInt(timeSplit[0]), Integer.parseInt(timeSplit[1]), Integer.parseInt(timeSplit[2]));

        return aDateTime;
		
	}
	
	public static void main(String[] args) {
		
//		long time = System.currentTimeMillis();
//		LocalDateTime localDateTime = Util.long2LocalDateTime(time);
//		String str = Util.localDateTime2String(localDateTime);
//		System.out.println("time = " + time);
//		System.out.println("localDateTime = " + localDateTime.toString());
//		System.out.println("str = " + str);
		
//		LocalDateTime now = LocalDateTime.now();
//		String s = Util.localDateTime2String(now);
//		System.out.println("now = " + now.toString());
//		System.out.println("s = " + s);
//		LocalDateTime localDateTime = Util.String2LocalDateTime(s);
//		System.out.println("localDateTime = " + localDateTime.toString());
		
		long time = System.currentTimeMillis();
		String dateTime = Util.localDateTime2String(Util.long2LocalDateTime(time));
		System.out.println(dateTime);
	}
	
}
	