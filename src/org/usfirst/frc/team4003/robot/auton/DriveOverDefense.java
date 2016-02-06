package org.usfirst.frc.team4003.robot.auton;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4003.robot.*;
import org.usfirst.frc.team4003.robot.commands.TrisonicsPID;
import org.usfirst.frc.team4003.robot.io.*;

/**
 *
 */
public class DriveOverDefense extends Command {
	final int PREDOWN =0;
    final int DOWN =1;
    final int STOP =2;
    int state =PREDOWN;
    double speed;
    TrisonicsPID pid = new TrisonicsPID(.02,0,0);
    Sensors sensors = Sensors.getInstance();
    final double PITCHDOWNLIMIT = -10;
    final double PITCHDOWNTHRESHOLD = -2;

    public DriveOverDefense(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.speed = speed;
    	pid.setTarget(0);
    	requires(Robot.strongHoldDrive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		double correction = pid.getCorrection(sensors.getYaw());
		if (correction < -speed)
			correction = -speed;
		if (correction > speed)
			correction = speed;
		switch (state) {
		case PREDOWN:
			Robot.strongHoldDrive.setPower(speed - correction, speed
					+ correction);
			if (sensors.getPitch() < sensors.getPitchBaseLine()
					+ PITCHDOWNLIMIT)
				state = DOWN;
			break;
		case DOWN:
			Robot.strongHoldDrive.setPower(speed - correction, speed
					+ correction);
			if (sensors.getPitch() > sensors.getPitchBaseLine()
					+ PITCHDOWNTHRESHOLD)
				state = STOP;
			break;
		case STOP:
			Robot.strongHoldDrive.setPower(0, 0);
			break;
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return state == STOP;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.strongHoldDrive.setPower(0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}