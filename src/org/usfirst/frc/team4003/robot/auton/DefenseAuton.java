package org.usfirst.frc.team4003.robot.auton;

import org.usfirst.frc.team4003.robot.commands.*;
import org.usfirst.frc.team4003.robot.io.*;
import org.usfirst.frc.team4003.robot.commands.actions.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DefenseAuton extends CommandGroup {
    public DefenseAuton(int defense) {
    	/*
    	if (defense == Sensors.RAMPART) {
    		addSequential(new RampartAuton()); 
    		return;
    	}
    	*/
    	if (defense == Sensors.CHEVAL) {
    		addSequential(new ChevalDeFrisAuton());
    		return;
    	}	
    	addSequential(new DriveToPoint(44, 0, 0.5, 0, false, true));
    	switch(defense) {
    	case Sensors.ROCKWALL:
    		addSequential(new DriveOverDefense(0.6));
    		break;
    	case Sensors.MOAT:
    		addSequential(new DriveOverDefense(0.7));
    		break;
    	case Sensors.ROUGHTERRAIN:
    		addSequential(new DriveOverDefense(0.6));
    		break;
    	case Sensors.RAMPART:
    		addSequential(new DriveOverDefense(0.75));
    		break;
    	}
    	addSequential(new DriveToPoint(Sensors.getInstance().getFinalDrive(), 0, 0.65, 0, true, false));
    	
    	addSequential(new AlignAndShoot());
        
    	// Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
