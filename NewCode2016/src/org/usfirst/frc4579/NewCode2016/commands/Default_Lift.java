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

import com.RoboEagles4579.filters.FirstOrderLPF;

/**
 *
 */
public class Default_Lift extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private static FirstOrderLPF inputLPF = new FirstOrderLPF(0.2);
	private static double teleopTimeElapsed;
	private static double maxAngleTeleop = 96.0;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public Default_Lift() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.lifter);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	Robot.lifter.enable();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double setpoint = inputLPF.filter(Robot.oi.getLiftStick().getZ());
    	
    	setpoint = (76.25 * setpoint) + 76.25;

    	Robot.lifter.setSetpoint(setpoint); // TODO: MATH HERE! ( I DON'T HAVE IT WITH ME AT THE MOMENT )
    	

    	double liftPID_out = Robot.lifter.getPIDController().get();
    	double liftPID_err = Robot.lifter.getPIDController().getError();
    	double feedback = Robot.lifter.getPot();
    	
    	System.out.println("\nLifter PID Output: " + liftPID_out + " | Error: " + liftPID_err + " | Setpoint: " + setpoint + " | Pot: " + feedback);
       	System.out.println("P: " + Robot.lifter.getPIDController().getP() + " I: " + Robot.lifter.getPIDController().getI() + " D:" + Robot.lifter.getPIDController().getD()+ "\n");
    	
       	teleopTimeElapsed = Timer.getFPGATimestamp() - Robot.teleopStartTime;
       	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.lifter.disable();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	end();
    	
    }
    
}
