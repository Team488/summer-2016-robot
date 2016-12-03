package competition.subsystems;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.operator_interface.OperatorInterface;
import competition.subsystems.collector.CollectorSubsystem;
import competition.subsystems.collector.commands.StopCollectorCommand;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.drive.commands.TankDriveWithGamepadCommand;
import competition.subsystems.feeder.FeederSubsystem;
import competition.subsystems.feeder.commands.LowerFeederCommand;
import competition.subsystems.hood.HoodSubsystem;
import competition.subsystems.hood.commands.StopHoodCommand;
import competition.subsystems.telemetry.TelemetrySubsystem;
import competition.subsystems.telemetry.commands.UpdateTelemetryCommand;
import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import competition.subsystems.turret.rotation.TurretRotationSubsystem;
import competition.subsystems.turret.rotation.commands.RotateTurretWithGamepadCommand;
import competition.subsystems.turret.shooter_wheel.commands.ControlShooterWheelWithGamepadCommand;
import competition.subsystems.turret.shooter_wheel.commands.StopShooterWheelCommand;

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
    
    public void setupDriveSubsystem(DriveSubsystem driveSubsystem, TankDriveWithGamepadCommand command) {
        driveSubsystem.setDefaultCommand(command);
    }
    
    @Inject
    public void setupTurretSubsystem(TurretRotationSubsystem turretRotSubsystem, RotateTurretWithGamepadCommand command) {
        turretRotSubsystem.setDefaultCommand(command);
    }
    
    @Inject 
    public void setupHoodSubsystem(HoodSubsystem hoodSubsystem, StopHoodCommand command){
        hoodSubsystem.setDefaultCommand(command);
    }
    
    @Inject
    public void setupShooterWheelCommands(ShooterWheelSubsystem hoodSubsystem, StopShooterWheelCommand command) {
        hoodSubsystem.setDefaultCommand(command);
    }
    
}
