// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4579.NewCode2016.subsystems;

import org.usfirst.frc4579.NewCode2016.RobotMap;
import org.usfirst.frc4579.NewCode2016.commands.*;

import com.RoboEagles4579.filters.AverageFilter;
import com.RoboEagles4579.math.Vector3d;
import com.RoboEagles4579.sensors.MPU_6050_I2C;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Measurement extends Subsystem {

	
	private static final double GsToAccel = 386.0885826772; // INCHES PER SECOND SQUARED
	private static final double MMtoInches = 0.0393701; //Millimeters to inches
	private static final double DegreesToRads = (Math.PI / 180.);
	private static final int calibrationIterations = 900;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final AnalogInput ultrasonic = RobotMap.measurementultrasonic;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final MPU_6050_I2C mpu = new MPU_6050_I2C();

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    private Vector3d acceleration = new Vector3d(),
    				 rawAcceleration = new Vector3d(),
    				 distance = new Vector3d(),
    				 relativeFieldPosition = new Vector3d();
    
    private double ultrasonicDistance = 0.,
    				robotRate = 0.,
    				rawGyro = 0.,
    				robotAngle = 0.;
    
    private Vector3d accelOffset = new Vector3d();
    private double gyroOffset = 0.;
    
    private AverageFilter accelerationX_avg = new AverageFilter(9);
    private AverageFilter accelerationY_avg = new AverageFilter(9);
    private AverageFilter accelerationZ_avg = new AverageFilter(9);
    private AverageFilter gyro_avg = new AverageFilter(9);
    private AverageFilter ultrasonic_avg = new AverageFilter(9);
    
    private boolean valid = false,
    				printReads = false;
    private double startTime = 0.,
    			   lastTime = 0.;
    
    
    public void initialize() {
    	
    	initAccelerometer();
    	
    	initGyro();
    	
    	initUltrasonic();
    	
    	startTime = getTime();
    	lastTime = startTime;
    	
    	setValid();
    	
    	System.out.println("***** MEASUREMENT INITIALIZED" + "\n");
    	
    }
    
    public void measure() {
    	
    	if(!valid) {
    		initialize();
    	}
    	
    	double time = getTime(),
    			deltaT = time - lastTime;
    	lastTime = time;
    	
    	// Find Filtered Accelerations and Gyro Rates
    	
    	rawAcceleration.X = mpu.read().getAccel().X;
    	rawAcceleration.Y = mpu.read().getAccel().Y;
    	rawAcceleration.Z = mpu.read().getAccel().Z;
    	
    	rawGyro = mpu.read().getGyro().Z;
    	
    	acceleration.X = accelerationX_avg.filter(rawAcceleration.X - accelOffset.X) * GsToAccel;
    	acceleration.Y = accelerationY_avg.filter(rawAcceleration.Y - accelOffset.Y) * GsToAccel;
    	acceleration.Z = accelerationZ_avg.filter(rawAcceleration.Z - accelOffset.Z) * GsToAccel;
    	
    	robotRate = gyro_avg.filter(rawGyro - gyroOffset);
    	
    	robotAngle += robotRate * deltaT;
    	
    	distance.X += (0.5) * acceleration.X * (deltaT*deltaT);
    	distance.Y += (0.5) * acceleration.Y * (deltaT*deltaT);
    	distance.Z += (0.5) * acceleration.Z * (deltaT*deltaT);
    	
    	relativeFieldPosition.X += ((0.5) * (acceleration.X) * (deltaT * deltaT)) * Math.sin(robotAngle * DegreesToRads);
    	relativeFieldPosition.Y += ((0.5) * (acceleration.Y) * (deltaT * deltaT)) * Math.cos(robotAngle * DegreesToRads);
    	

    	double distance = (ultrasonic.getVoltage() * 5.) / .00488; //in millimeters
		ultrasonicDistance = ultrasonic_avg.filter(1.044*(distance * MMtoInches)-0.8); //	These values are from hardware testing
    	
    	print();
    	
    }
    
    private void initAccelerometer() {
    	
    	//Find Offsets
    	
    	accelOffset.reset();
    	
    	double sumX = 0.,
    			sumY = 0.,
    			sumZ = 0.;
    	
    	for(int i = 0; i <= calibrationIterations; i++) {
    		
    		sumX += mpu.read().getAccel().X;
    		sumY += mpu.read().getAccel().Y;
    		sumZ += mpu.read().getAccel().Z;
    		
    	}
    	
    	accelOffset.X = sumX / calibrationIterations;
    	accelOffset.Y = sumY / calibrationIterations;
    	accelOffset.Z = sumZ / calibrationIterations;
    	
    	System.out.println("***** Accelerometer Calibrated:   X-OFFSET: " + accelOffset.X + "   Y-OFFSET: " + accelOffset.Y + "   Z-OFFSET: " + accelOffset.Z + "\n");
    	
    }
    
    private void initGyro() {
    	
    	//Find Offset
    	
    	gyroOffset = 0.;
    	
    	double sum = 0.;
    	
    	for(int i = 0; i <= calibrationIterations; i++) {
    		
    		sum += mpu.read().getGyro().Z;
    		
    	}
    	
    	gyroOffset = sum / calibrationIterations;
    	
    	System.out.println("***** Gyro Calibrated:   Z-OFFSET: " + gyroOffset);
    	
    }
    
    private void initUltrasonic() {
    	
    	//Reset to zero
    	
    	ultrasonicDistance = 0.;
    	
    	
    }
    
    public Vector3d getDistance() {
    	return distance;
    }
    
    public Vector3d getAcceleration() {
    	return acceleration;
    }
    
    public Vector3d getRelativeFieldPosition() {
    	return relativeFieldPosition;
    }
    
    public double getAngle() {
    	return robotAngle;
    }
    
    public double getRate() {
    	return robotRate;
    }
    
    public double getUltrasonic() {
				
		return ultrasonicDistance;
		
    }
    
    private void setValid() {
    	
    	valid = true;
    	
    }
    
    public void setInvalid() {
    	
    	valid = false;
    	
    }
    
    public boolean isValid() {
    	return valid;
    }
    
    private double getTime() { //Returns Elapsed Time
    	return Timer.getFPGATimestamp() - startTime;
    }
    
    public void setPrints(boolean prints) {
    	printReads = prints;
    }
    
    private void print() {
    	
    	if(printReads) {
    		
    		System.out.println("RAW ACCELERATION: |X: " + rawAcceleration.X + " |Y: " + rawAcceleration.Y + " |Z: " + rawAcceleration.Z);
    		System.out.println("ACCELERATION OFFSETS: |X: " + accelOffset.X + " |Y: " + accelOffset.Y + " |Z: " + accelOffset.Z);
    		System.out.println("FILTERED ACCELERATION: |X: " + acceleration.X + " |Y: " + acceleration.Y + " |Z: " + acceleration.Z + "\n");
    		
    		System.out.println("DISTANCE: |X: " + distance.X + " |Y: " + distance.Y + " |Z: " + distance.Z);
    		System.out.println("RELATIVE FIELD POSITION: |X:" + relativeFieldPosition.X + " |Y: " + relativeFieldPosition.Y + " |Z: " + relativeFieldPosition.Z);
    		
    		System.out.println("RAW GYRO: " + rawGyro + " |GYRO OFFSET: " + gyroOffset + " |FILTERED GYRO: " + robotRate + "\n");
    		
    		System.out.println("ULTRASONIC DISTANCE: " + ultrasonicDistance);
    		
    	}
    	
    }
    

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Default_Measure());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

