package competition.subsystems.drive.commands;

import com.google.inject.Inject;

import competition.operatorinterface.OI;
import competition.subsystems.drive.DriveSubsystem;
import xbot.common.command.BaseCommand;


public class TankDriveWithJoysticksCommand extends BaseCommand {

	final DriveSubsystem driveSubsystem;
	final OI oi;
	
	@Inject
    public TankDriveWithJoysticksCommand(OI oi, DriveSubsystem driveSubsystem)
    {
        this.oi = oi;
        this.driveSubsystem = driveSubsystem;
        this.requires(this.driveSubsystem);
    }
	
	@Override
	public void initialize() {
		
	}

	@Override
	public void execute() {
		driveSubsystem.tankDrive(
				oi.leftJoystick.getVector().y,
				oi.rightJoystick.getVector().y);
		
	}

}
