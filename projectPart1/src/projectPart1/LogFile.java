package projectPart1;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

public class LogFile {
	private static int LogNo = 1;
	private static Random rand = new Random();
	private static String LogFileName = "Log" + rand.nextInt(1000) + ".txt";
	public static void log(int [] readings,int prevAlt, String message) {
		FileWriter writer;
		try {
			writer = new FileWriter(LogFileName, true);
			writer.write(LogNo + ". " + LocalDateTime.now() + " | Sensor A: " + readings[0] + " | Sensor B: " + readings[1] + " | Sensor C: " + readings[2] + " | Final Reading: " + prevAlt + " | Comments: " + message + "\n");
			LogNo++;
			writer.close();
		} catch (IOException e) {
			System.out.println("Logging Error");
		}
	}

}
