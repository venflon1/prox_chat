package roberto.day16;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class Util {

	public static LocalDateTime long2LocalDateTime(Long longParam) {
		return  LocalDateTime.ofInstant(Instant.ofEpochMilli(longParam), TimeZone
                															.getDefault().toZoneId());
	}
	
	public static String localDateTime2String(LocalDateTime localDateTimeParam) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("YYYY-MM-dd hh:mm:ss");

		return localDateTimeParam.format(formatter).toString();
	}
	
	public static long localDateTimeWithZone2long(LocalDateTime localDateTimeParam) {
		System.out.println("Zone " + ZoneId.systemDefault());
		System.out.println("atZone = " + localDateTimeParam.atZone(ZoneId.systemDefault()));
		System.out.println("atZoneToInstant = " + localDateTimeParam.atZone(ZoneId.systemDefault()).toInstant());
		System.out.println("localDatetime = " + localDateTimeParam.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		return localDateTimeParam.atZone(ZoneId.of("Europe/Rome")).toInstant().toEpochMilli();
	}

	
	public static LocalDateTime String2LocalDateTime(String stringParam) {
		String[] splitDateTime = stringParam.split(" ");
		String date = splitDateTime[0];
		String time = splitDateTime[1];
		String[] dateSplit = date.split("-");

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
	