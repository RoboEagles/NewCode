// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4579.NewCode2016;

import org.usfirst.frc4579.NewCode2016.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public JoystickButton shootBtn;
    public JoystickButton loadBtn;
    public JoystickButton ejectBtn;
    public JoystickButton reverseShoot;
    public Joystick driveStick;
    public JoystickButton extendArm;
    public JoystickButton retractArm;
    public Joystick liftStick;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        liftStick = new Joystick(1);
        
        retractArm = new JoystickButton(liftStick, 1);
        retractArm.whenPressed(new RetractArms());
        extendArm = new JoystickButton(liftStick, 1);
        extendArm.whenPressed(new ExtendArms());
        driveStick = new Joystick(0);
        
        reverseShoot = new JoystickButton(driveStick, 1);
        reverseShoot.whileHeld(new ReverseShoot());
        ejectBtn = new JoystickButton(driveStick, 3);
        ejectBtn.whileHeld(new EjectBall());
        loadBtn = new JoystickButton(driveStick, 2);
        loadBtn.whileHeld(new LoadBall());
        shootBtn = new JoystickButton(driveStick, 1);
        shootBtn.whenPressed(new ShootGroup());


        // SmartDashboard Buttons
        SmartDashboard.putData("Default_Drive", new Default_Drive());
        SmartDashboard.putData("Auto_CrossDefense", new Auto_CrossDefense());
        SmartDashboard.putData("Auto_DoNothing", new Auto_DoNothing());
        SmartDashboard.putData("Default_Lift", new Default_Lift());
        SmartDashboard.putData("TestRoutine", new TestRoutine());
        SmartDashboard.putData("Default_Measure", new Default_Measure());
        SmartDashboard.putData("ExtendArms", new ExtendArms());
        SmartDashboard.putData("RetractArms", new RetractArms());
        SmartDashboard.putData("EjectBall", new EjectBall());
        SmartDashboard.putData("LoadBall", new LoadBall());
        SmartDashboard.putData("ShootBall", new ShootBall());
        SmartDashboard.putData("ReverseShoot", new ReverseShoot());
        SmartDashboard.putData("ShootGroup", new ShootGroup());
        SmartDashboard.putData("ChamberBall", new ChamberBall());

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public Joystick getDriveStick() {
        return driveStick;
    }

    public Joystick getLiftStick() {
        return liftStick;
    }


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    
    public void smartDashboardDisplay() {
    	
    	SmartDashboard.putNumber("Acceleration X: ", Robot.measurement.getAcceleration().X);
    	SmartDashboard.putNumber("Acceleration Y: ", Robot.measurement.getAcceleration().Y);
    	SmartDashboard.putNumber("Acceleration Z: ", Robot.measurement.getAcceleration().Z);
    	
    	SmartDashboard.putNumber("Distance X: ", Robot.measurement.getDistance().X);
    	SmartDashboard.putNumber("Distance Y: ", Robot.measurement.getDistance().Y);
    	SmartDashboard.putNumber("Distance Z: ", Robot.measurement.getDistance().Z);
    	
    	SmartDashboard.putNumber("Relative Field Position X: ", Robot.measurement.getRelativeFieldPosition().X);
    	SmartDashboard.putNumber("Relative Field Position Y: ", Robot.measurement.getRelativeFieldPosition().Y);
    	SmartDashboard.putNumber("Relative Field Position Z: ", Robot.measurement.getRelativeFieldPosition().Z);
    	
    	SmartDashboard.putNumber("Rate of Turn: ", Robot.measurement.getRate());
    	SmartDashboard.putNumber("Robot Angle: ", Robot.measurement.getAngle()); 
    	
    	SmartDashboard.putNumber("Ultrasonic Distance: ", Robot.measurement.getUltrasonic()); 
    	
    }
    
}

