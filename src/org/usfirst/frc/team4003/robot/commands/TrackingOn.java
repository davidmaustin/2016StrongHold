package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TrackingOn extends Command {
	boolean on;
	TrackTarget trackTarget;
    public TrackingOn(boolean on) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.on = on;
    	trackTarget = null;
    	
    	requires(Robot.turretSpin);
    	requires(Robot.turretTilt);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.NIVision) Robot.cameras.setTracking(on);
    	SmartDashboard.putBoolean("on", on);
    	
    	if (on) {
    		Robot.enableTargetTracking();
    		if (trackTarget == null) {
    			trackTarget = new TrackTarget();
    			trackTarget.start();
    		}
    	} else {
    		Robot.disableTargetTracking();
    		if (trackTarget == null) return;
    		trackTarget.setFinished(true);
    		trackTarget.cancel();
    		trackTarget = null;
    		//Robot.turretTilt.setPower(0);
    		//Robot.turretSpin.setPower(0);
    		TurretTiltCommand cmd = new TurretTiltCommand();
    		cmd.start();
    		
    	}
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
