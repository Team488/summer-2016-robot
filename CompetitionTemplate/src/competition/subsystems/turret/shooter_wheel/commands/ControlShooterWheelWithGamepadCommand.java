package competition.subsystems.turret.shooter_wheel.commands;

import com.google.inject.Inject;

import competition.operator_interface.OperatorInterface;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import xbot.common.command.BaseCommand;

public class ControlShooterWheelWithGamepadCommand extends BaseCommand {

    final ShooterWheelSubsystem shooterSubsystem;
    final OperatorInterface oi;

    @Inject
    public ControlShooterWheelWithGamepadCommand(OperatorInterface oi, ShooterWheelSubsystem shooterSubsystem) {
        this.oi = oi;
        this.shooterSubsystem = shooterSubsystem;
        this.requires(this.shooterSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        shooterSubsystem.setShooterTarget(oi.leftJoystick.getRawAxis(3) * shooterSubsystem.getShooterLaunchSpeed());
        shooterSubsystem.updateMotorState();
    }
}
