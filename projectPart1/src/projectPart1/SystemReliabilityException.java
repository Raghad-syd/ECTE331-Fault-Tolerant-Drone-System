package projectPart1;

import java.io.IOException;
/**
 * Custom exception
 * Thrown when the system enters "safe mode"
 * i.e two or more sensors fail
 * or when there is no majority voting
 */

public class SystemReliabilityException extends IOException {
	public SystemReliabilityException(String message) {
		super(message);
	}

}
