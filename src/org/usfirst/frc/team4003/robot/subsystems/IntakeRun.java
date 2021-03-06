package org.usfirst.frc.team4003.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.*;

import org.usfirst.frc.team4003.robot.RobotMap;
import org.usfirst.frc.team4003.robot.commands.*;
public class IntakeRun extends Subsystem {
    Talon intake0 = new Talon(RobotMap.INTAKEMOTOR);
    
    public IntakeRun() {
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new IntakeRunCommand());
    }
    
    public void setPower(double power) {
    	intake0.set(power);
    }
}

