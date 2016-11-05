package competition.subsystems;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.collector.CollectorSubsystem;
import competition.subsystems.collector.commands.StopCollectorCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.drive.commands.TankDriveWithGamepadCommand;
import competition.subsystems.feeder.FeederSubsystem;
import competition.subsystems.feeder.commands.LowerFeederCommand;
import competition.subsystems.telemetry.TelemetrySubsystem;
import competition.subsystems.telemetry.commands.UpdateTelemetryCommand;
import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import competition.subsystems.turret.rotation.TurretRotationSubsystem;
import competition.subsystems.turret.rotation.commands.RotateTurretWithGamepadCommand;
import competition.subsystems.turret.shooter_wheel.commands.ControlShooterWheelWithGamepadCommand;

@Singleton
public class SubsystemDefaultCommandMap {
    // For setting the default commands on subsystems
    
    @Inject 
    public void setupFeederSubsystem(FeederSubsystem subsystem, LowerFeederCommand command){
        subsystem.setDefaultCommand(command);
    }
    
    @Inject
    public void setupCollectorSubsystem(CollectorSubsystem collectorSubsystem, StopCollectorCommand command){
        collectorSubsystem.setDefaultCommand(command);
    }
    
    @Inject
    public void setupTelemetrySubsystem(TelemetrySubsystem subsystem, UpdateTelemetryCommand command) {
        subsystem.setDefaultCommand(command);
    }
    
    @Inject
    public void setupDriveSubsystem(DriveSubsystem driveSubsystem, TankDriveWithGamepadCommand command) {
        driveSubsystem.setDefaultCommand(command);
    }
    
    @Inject
    public void setupShooterWheelSubsystem(ShooterWheelSubsystem shooterSubsystem, ControlShooterWheelWithGamepadCommand command) {
        shooterSubsystem.setDefaultCommand(command);
    }
    
    @Inject
    public void setupShooterWheelSubsystem(TurretRotationSubsystem turretRotSubsystem, RotateTurretWithGamepadCommand command) {
        turretRotSubsystem.setDefaultCommand(command);
    }
}
