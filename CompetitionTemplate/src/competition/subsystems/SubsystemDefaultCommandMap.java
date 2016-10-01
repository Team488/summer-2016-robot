package competition.subsystems;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.drive.commands.TankDriveWithGamepadCommand;
import competition.subsystems.drive.commands.TankDriveWithJoysticksCommand;
import competition.subsystems.turret.rotation.TurretRotationSubsystem;
import competition.subsystems.turret.rotation.commands.RotateTurretWithGamepadCommand;
import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import competition.subsystems.turret.shooter_wheel.commands.ControlShooterWheelWithGamepadCommand;

@Singleton
public class SubsystemDefaultCommandMap {
    // For setting the default commands on subsystems

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
