package competition.subsystems.telemetry.commands;

import com.google.inject.Inject;

import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.telemetry.TelemetrySubsystem;
import competition.subsystems.turret.rotation.TurretRotationSubsystem;
import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import xbot.common.command.BaseCommand;

public class UpdateTelemetryCommand extends BaseCommand {

    DriveSubsystem drive;
    ShooterWheelSubsystem wheel;
    TurretRotationSubsystem turretRotation;
    
    @Inject
    public UpdateTelemetryCommand(
            TelemetrySubsystem subsystem,
            DriveSubsystem drive,
            ShooterWheelSubsystem wheel,
            TurretRotationSubsystem turretRotation) {
        this.requires(subsystem);
        this.setRunWhenDisabled(true);
        this.drive = drive;
        this.wheel = wheel;
        this.turretRotation = turretRotation;
    }
    
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute() {
        this.wheel.updateTelemetry();
    }

}
