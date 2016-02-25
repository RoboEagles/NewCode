// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4579.NewCode2016.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4579.NewCode2016.Robot;
import org.usfirst.frc4579.NewCode2016.RobotMap;

/**
 *
 */
public class TestRoutine extends Command {
	
	private static final double testSpeeds = 0.5,
								testLiftSetpoint = 0.5,
								testTimeout = 5.0,
								testDriveFwd = 0.5,
								testDriveRot = 0.0,
								testSolenoidDelay = 2.0;
	
	private static final boolean testDrive = false,
								 testSolenoids = false;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public TestRoutine() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	
    	// LIFTER PID DIAGNOSTICS
    	
    	Robot.lifter.enable();
    	
    	Robot.lifter.setSetpoint(testLiftSetpoint);
    	
    	double liftPID_out = Robot.lifter.getPIDController().get();
    	double liftPID_err = Robot.lifter.getPIDController().getError();
    	
    	System.out.println("\nLifter PID Initial Output: " + liftPID_out + " | Initial Error: " + liftPID_err + " | Initial Setpoint: " + testLiftSetpoint);
    	System.out.println("P: " + Robot.lifter.getPIDController().getP() + " I: " + Robot.lifter.getPIDController().getI() + " D:" + Robot.lifter.getPIDController().getD()+ "\n");
    	
    	Robot.lifter.disable();
    	
    	// PERFORM SHOOTING TEST
    	System.out.println("***** SHOOTING TEST *****");
    	
    	Command shootCmd = new ShootGroup();
    	
    	shootCmd.start();
    	
    	Timer.delay(RobotMap.SHOOT_DELAY);
    	
    	System.out.println("***** SHOOTING TEST COMPLETE *****");
    	
    	// TEST ARM EXTENSION IF NECESSARY
    	if(testSolenoids) {
    		
    		System.out.println("***** SOLENOID TEST *****");
    		
    		Robot.lifter.extend();
    		Timer.delay(testSolenoidDelay);
    		Robot.lifter.retract();
    		
    		System.out.println("***** SOLENOID TEST COMPLETE *****");
    		
    		
    	} else {
    		
    		System.out.println(" SOLENOID NOT TESTED ");
    		
    	}
    	
    	// PRINT WHETHER OR NOT WE ARE TESTING DRIVING
    	if(!testDrive) {
    		
    		System.out.println(" DRIVE NOT TESTED ");
    		
    	}
    	

    	//  SET MEASUREMENT TO PRINT DIAGNOSTICS
    	Robot.measurement.setPrints(true);
    	
    	
    	setTimeout(testTimeout);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.loader.set(testSpeeds);
    	Robot.shooter.set(testSpeeds);
    	
    	if(testDrive) {
    		
    		Robot.drivetrain.drive(testDriveFwd, testDriveRot);
    		
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.loader.stop();
    	Robot.shooter.stop();
    	
    	Robot.measurement.setPrints(false);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    	
    }
}
