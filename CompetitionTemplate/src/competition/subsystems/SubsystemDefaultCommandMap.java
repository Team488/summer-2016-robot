package competition.subsystems;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.sensor.ValidateIMUSubsystem;
import competition.subsystems.sensor.commands.ValidateIMUCommand;

@Singleton
public class SubsystemDefaultCommandMap {
	// For setting the default commands on subsystems
	
	/*@Inject
	public void setupDriveSubsystem(
	        DriveSubsystem driveSubsystem,
	        TankDriveWithJoysticksCommand command)
	{
		driveSubsystem.setDefaultCommand(command);
	}*/
	
   @Inject
    public void setupSensorSubsystem(
            ValidateIMUSubsystem sensorSubsystem,
            ValidateIMUCommand command)
    {
       sensorSubsystem.setDefaultCommand(command);
    }
}
