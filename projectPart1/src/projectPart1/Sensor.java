package projectPart1;

import java.util.Random;
/**
 * The Sensor class simulates a drone sensor that generates altitude readings.
 *
 * Each sensor can:
 * Return a valid reading (0–200).
 * Return a corrupted reading (>200 or <0).
 * Fail and throw a SensorReadException.
 */
public class Sensor {
	/**
     * Unique identifier for the sensor (A, B, C).
     */
	private String sensorID; //Variable to store the sensor ID
	

    /**
     * Random number generator used to simulate sensor behavior.
     */
	private Random random; //Variable to generate a random sensor value
	
	/**
     * Constructs a Sensor with a given ID.
     *
     * @param sensorID the identifier of the sensor.
     * @param random random sensor value.
     */
	//Constructor
	public Sensor(String sensorID) {
		this.sensorID = sensorID;
		random = new Random();
	}
	/**
     * Simulates reading a value from the sensor.
     *
     * Behavior:
     * chance < 15: sensor fails and throws exception.
     * chance < 30: returns corrupted value (>200).
     * otherwise: returns valid value (0–200).
     *
     * @return simulated sensor reading.
     * @throws SensorReadException if the sensor fails.
     */
	
	public int sensorRead() throws SensorReadException {
		int chance = random.nextInt(99); //Variable to store the random sensor value
		if(chance < 15) {
			throw new SensorReadException(sensorID + " Failed!");  //Sensor Failure
		} else if(chance < 30) {
			System.out.println("Sensor " + sensorID + "Produced a corrupted reading.");
			return 201 + random.nextInt(99); //corrupted value
		} else {
			return random.nextInt(201); //return value in range
		}
		
	}
	/**
     * Returns the sensor ID.
     *
     * @return sensor identifier
     */
	//getter for the sensor ID
	public String getSensorID() {
		return sensorID;
	}

}
