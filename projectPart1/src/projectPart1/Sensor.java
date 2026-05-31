package projectPart1;

import java.util.Random;

public class Sensor {
	private String sensorID;
	private Random random;
	
	public Sensor(String sensorID) {
		this.sensorID = sensorID;
		random = new Random();
	}
	
	public int sensorRead() throws SensorReadException {
		int chance = random.nextInt(99); 
		if(chance < 15) {
			throw new SensorReadException(sensorID + "Failed!");
		} else if(chance < 30) {
			return 200 + random.nextInt(99);
		} else {
			return random.nextInt(200);
		}
		
	} public String getSensorID() {
		return sensorID;
	}

}
