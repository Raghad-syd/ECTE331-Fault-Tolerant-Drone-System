package projectPart1;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogFIle {
	private static int LogNo = 1;
	public static void log(int [] readings,int FinalAlt, String message) {
		FileWriter writer;
		try {
			writer = new FileWriter("Log.txt", true);
			writer.write(LogNo + ". " + LocalDateTime.now() + " | Sensor A: " + readings[0] + " | Sensor B: " + readings[1] + " | Sensor C: " + " | Final Reading: " + FinalAlt + " | Comments: " + message);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Logging Error");
		}
	}

}
