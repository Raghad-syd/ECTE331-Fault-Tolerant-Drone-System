package projectPart1;

import java.io.IOException;
/**
 * Custom exception
 * Thrown when the sensor fails  
 * i.e when value generated is <15.
 */
public class SensorReadException extends IOException {
	public SensorReadException(String message) {
		super(message);
	}

}
