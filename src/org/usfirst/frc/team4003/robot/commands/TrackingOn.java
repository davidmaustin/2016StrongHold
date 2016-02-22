package org.usfirst.frc.team4003.robot.commands;

import org.usfirst.frc.team4003.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

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
    	trackTarget = new TrackTarget();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (on) {
    		Robot.enableTargetTracking();
    		trackTarget = new TrackTarget();
    		trackTarget.start();
    	} else {
    		Robot.disableTargetTracking();
    		trackTarget.cancel();
    		TurretTiltCommand tiltCommand = new TurretTiltCommand();
    		tiltCommand.start();
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
