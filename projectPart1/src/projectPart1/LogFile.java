package projectPart1;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Random;

/**
 * The LogFile class is responsible for creating and maintaining a log file
 * for each execution of the application.
 *
 * It records sensor readings, the final computed altitude, time stamps,
 * and system comments for tracking purposes.
 */
public class LogFile {

    /**
     * Tracks the log entry number for each recorded line in the file.
     */
    private static int LogNo = 1;

    /**
     * Random number generator used to create a unique log file name.
     */
    private static Random rand = new Random();

    /**
     * The name of the log file generated for this execution.
     * A random number is appended to avoid overwriting previous logs.
     */
    private static String LogFileName = "Log" + rand.nextInt(1000) + ".txt";

    /**
     * Writes a log entry to the log file containing sensor data,
     * final altitude, time stamp, and system message.
     *
     * Each call appends a new entry to the file and increments the log number.
     *
     * @param readings array of sensor readings (A, B, C).
     * @param prevAlt the final computed or previous altitude value.
     * @param message system status or comment describing the result.
     */
    public static void log(int[] readings, int prevAlt, String message, String outlierSensor) {
        FileWriter writer;

        try {
            writer = new FileWriter(LogFileName, true);

            writer.write(
                LogNo + ". " +
                LocalDateTime.now() +
                " | Sensor A: " + readings[0] +
                " | Sensor B: " + readings[1] +
                " | Sensor C: " + readings[2] +
                " | Final Reading: " + prevAlt +
                " | Outlier = " + outlierSensor +
                " | Comments: " + message + "\n"
            );

            LogNo++;
            writer.close();

        } catch (IOException e) {
            System.out.println("Logging Error");
        }
    }
}