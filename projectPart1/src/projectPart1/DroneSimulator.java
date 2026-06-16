package projectPart1;

/**
 * The DroneSimulator class simulates a drone altitude monitoring system
 * that processes readings from three sensors and determines the most
 * reliable altitude using majority voting.
 *
 * The system also tracks consecutive failures and triggers a safe mode
 * if reliability conditions are not met repeatedly.
 */

public class DroneSimulator {
	/**
	 * Stores previous altitude.
	 */
    private int prevAlt; 
    
    /**
     * Tracks consecutive failures.
     */
    private int failureCount;
    
    /**
     * Stores the system's latest message.
     */
    private String comment;
    
    /**
     * Stores the outlier sensor
     */
    private String outlierSensor;
    
    
    /**
    * Constructs a new DroneSimulator instance and initializes
    * all system values to default states.
    */
    public DroneSimulator() {
        prevAlt = 0;
        failureCount = 0;
        comment = "System initialized.";
    }
    
    /**
     * Returns the latest system status message.
     *
     * @return a string describing the most recent system action.
     */

    public String getComment() {
        return comment;
    }
    /**
     * Returns the sensor ID that did not agree with the other sensors.
     * @return a string with the sensorID
     */
    
    public String getOutlierSensor() {
        return outlierSensor;
    }
    /**
     *Returns the failure count for reliability checking.
     *@returns an integer with the total number of failures
     */
    public int getFailureCount() {
        return failureCount;
    }
    /**
     * Returns the previously confirmed valid altitude.
     *
     * @return the last valid altitude value.
     */
    public int getPrevAlt() {
    	return prevAlt;
    }


    /**
     * Determines the drone altitude using three sensor readings.
     *
     * The method applies majority voting among valid sensor values
     * (0–200 range). If fewer than two valid readings exist or no
     * agreement is found, the previous altitude is used instead.
     *
     * If two consecutive failures occur, the system enters safe mode
     * and throws a SystemReliabilityException.
     *
     * @param readings an array of three sensor readings.
     * @return the determined altitude or previous altitude if unreliable.
     * @throws SystemReliabilityException if the system enters safe mode.
     */
    public int determineAlt(int[] readings) throws SystemReliabilityException {

        // Count valid sensor readings
        int validCount = 0;
        //Check if values is within range
        for (int i = 0; i < readings.length; i++) {
            if (readings[i] >= 0 && readings[i] <= 200) {
                validCount++;
            }
        }

        // Fewer than 2 valid sensors
        if (validCount < 2) {
            failureCount++;
            comment = "Insufficient valid sensor readings. Using previous altitude.";

            if (failureCount >= 2) {
                throw new SystemReliabilityException("SAFE MODE ACTIVATED!"); //Enter safe mode if 2 or more consecutive failures
            }

            return prevAlt; //Return the previous altitude
        }

        // Majority voting, A = B
        if (readings[0] == readings[1]
                && readings[0] >= 0
                && readings[0] <= 200) {

            prevAlt = readings[0];
            failureCount = 0;
            comment = "Majority voting A & B.";
            return readings[0];
        }

        // Majority voting, B = C
        else if (readings[1] == readings[2]
                && readings[1] >= 0
                && readings[1] <= 200) {

            prevAlt = readings[1];
            failureCount = 0;
            comment = "Majority voting B & C.";
            outlierSensor = "A";
            return readings[1];
        }

        // Majority voting, A = C
        else if (readings[0] == readings[2]
                && readings[0] >= 0
                && readings[0] <= 200) {

            prevAlt = readings[0];
            failureCount = 0;
            comment = "Majority voting A & C.";
            outlierSensor = "B";
            return readings[0];
        }
        
        else if (readings[0] == readings[1] && readings[1] == readings[0]
                && readings[0] >= 0
                && readings[0] <= 200) {

            prevAlt = readings[0];
            failureCount = 0;
            comment = "Majority voting A, B & C.";
            outlierSensor = "None.";
            return readings[0];
        }

        // No agreement among valid sensors
        failureCount++;
        comment = "No sensor agreement. Using previous altitude.";
        outlierSensor = "A, B & C.";
        //If there is 2 consecutive sensor failures
        if (failureCount >= 2) {
            throw new SystemReliabilityException("SAFE MODE ACTIVATED!"); 
        }

        return prevAlt;
    }
}
