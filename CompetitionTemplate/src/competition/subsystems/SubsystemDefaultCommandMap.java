package competition.subsystems;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.drive.commands.TankDriveWithJoysticksCommand;
import competition.subsystems.telemetry.TelemetrySubsystem;
import competition.subsystems.telemetry.commands.UpdateTelemetryCommand;
import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import competition.subsystems.turret.shooter_wheel.commands.StopShooterWheelCommand;

@Singleton
public class SubsystemDefaultCommandMap {
    // For setting the default commands on subsystems

    @Inject
    public void setupTelemetrySubsystem(TelemetrySubsystem subsystem, UpdateTelemetryCommand command) {
        subsystem.setDefaultCommand(command);
    }
    
    @Inject
    public void setupDriveSubsystem(DriveSubsystem driveSubsystem, TankDriveWithJoysticksCommand command) {
        driveSubsystem.setDefaultCommand(command);
    }
    
}
