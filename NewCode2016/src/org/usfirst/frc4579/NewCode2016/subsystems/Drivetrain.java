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
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

import com.RoboEagles4579.filters.FirstOrderLPF;

import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Drivetrain extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private final SpeedController leftMotor = RobotMap.drivetrainleftMotor;
    private final SpeedController rightMotor = RobotMap.drivetrainrightMotor;
    private final RobotDrive drive = RobotMap.drivetraindrive;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private static FirstOrderLPF fwdLPF = new FirstOrderLPF(0.2);
    private static FirstOrderLPF sideLPF = new FirstOrderLPF(0.4); // 0.4 per Ivan's request

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public void drive(double fwd, double rot) {
    	
    	
    	fwd = fwdLPF.filter(fwd);
    	rot = sideLPF.filter(rot);
    	
    	fwd = fwd == 0 ? 0 : (fwd*fwd)*(fwd/Math.abs(fwd));
    	rot = rot == 0 ? 0 : (rot*rot)*(rot/Math.abs(rot));
    	
    	drive.arcadeDrive(fwd, rot);
    	
    }
    
    public void stop() {
    	
    	leftMotor.set(0.0);
    	rightMotor.set(0.0);
    	
    }

    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        setDefaultCommand(new Default_Drive());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}

