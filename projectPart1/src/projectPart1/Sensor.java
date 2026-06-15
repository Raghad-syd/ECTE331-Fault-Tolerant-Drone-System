package projectPart1;

import java.util.Random;

public class Sensor {
	private String sensorID; //Variable to store the sensor ID
	private Random random; //Variable to generate a random sensor value
	
	//Constructor
	public Sensor(String sensorID) {
		this.sensorID = sensorID;
		random = new Random();
	}
	
	public int sensorRead() throws SensorReadException {
		int chance = random.nextInt(99); //Variable to store the random sensor value
		if(chance < 15) {
			throw new SensorReadException(sensorID + " Failed!");  //Sensor Failure
		} else if(chance < 30) {
			return 201 + random.nextInt(99); //corrupted value
		} else {
			return random.nextInt(201); //return value in range
		}
		
	} //getter for the sensor ID
	public String getSensorID() {
		return sensorID;
	}

}
