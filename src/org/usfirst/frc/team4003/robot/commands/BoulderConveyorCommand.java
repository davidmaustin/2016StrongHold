package org.usfirst.frc.team4003.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4003.robot.*;

/**
 *
 */
public class BoulderConveyorCommand extends Command {

    public BoulderConveyorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.boulderConveyor);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//
    	//double power = -Robot.oi.operator.getRightTrigger()+
    	//		Robot.oi.operator.getLeftTrigger();
    	//if(Math.abs(power)<.1)power = 0;
    	double power = 0;
    	//SmartDashboard.putNumber("conveyor", power);
    	
    	double operatorPower = Robot.oi.operator.getLeftJoyY();
    	if (Math.abs(operatorPower) > 0.2) {power = operatorPower;}
    	
    	Robot.boulderConveyor.setPower(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.boulderConveyor.setPower(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
