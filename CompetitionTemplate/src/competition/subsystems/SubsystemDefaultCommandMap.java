package competition.subsystems;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.drive.commands.TankDriveWithJoysticksCommand;
import competition.subsystems.sensor.SensorSubsystem;
import competition.subsystems.sensor.commands.GetGyroPropertiesCommand;
import edu.wpi.first.wpilibj.Gyro;

@Singleton
public class SubsystemDefaultCommandMap {
	// For setting the default commands on subsystems
	
	@Inject
	public void setupDriveSubsystem(
	        DriveSubsystem driveSubsystem,
	        TankDriveWithJoysticksCommand command)
	{
		driveSubsystem.setDefaultCommand(command);
	}
	
   @Inject
    public void setupSensorSubsystem(
            SensorSubsystem sensorSubsystem,
            GetGyroPropertiesCommand command)
    {
       sensorSubsystem.setDefaultCommand(command);
    }
}
