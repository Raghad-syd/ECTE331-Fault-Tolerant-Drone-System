package projectPart1;

public class DroneSimulator {
    
    private int prevAlt; //Stores previous altitude
    private int failureCount;
    private String comment;
    //Constructor to initialize values
    public DroneSimulator() {
    	//Initialize the system
        prevAlt = 0;
        failureCount = 0;
        comment = "System initialized.";
    }
    //Getters

    public String getComment() {
        return comment;
    }
    public int getPrevAlt() {
    	return prevAlt;
    }

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
            return readings[1];
        }

        // Majority voting, A = C
        else if (readings[0] == readings[2]
                && readings[0] >= 0
                && readings[0] <= 200) {

            prevAlt = readings[0];
            failureCount = 0;
            comment = "Majority voting A & C.";
            return readings[0];
        }

        // No agreement among valid sensors
        failureCount++;
        comment = "No sensor agreement. Using previous altitude.";
        
        //If there is 2 consecutive sensor failures
        if (failureCount >= 2) {
            throw new SystemReliabilityException("SAFE MODE ACTIVATED!"); 
        }

        return prevAlt;
    }
}
