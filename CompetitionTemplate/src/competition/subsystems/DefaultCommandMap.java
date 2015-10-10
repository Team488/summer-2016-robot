package competition.subsystems;

import com.google.inject.Inject;

import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.drive.commands.TankDriveWithJoysticksCommand;

public class DefaultCommandMap {
	@Inject
	public void setupDriveSubsystem(
	        DriveSubsystem driveSubsystem,
	        TankDriveWithJoysticksCommand command)
	{
		driveSubsystem.setDefaultCommand(command);
	}
}
