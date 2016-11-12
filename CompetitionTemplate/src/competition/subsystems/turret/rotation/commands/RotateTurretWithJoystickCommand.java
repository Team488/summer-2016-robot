package competition.subsystems.turret.rotation.commands;

import com.google.inject.Inject;

import competition.operator_interface.OperatorInterface;
import competition.subsystems.turret.rotation.TurretRotationSubsystem;
import xbot.common.command.BaseCommand;

public class RotateTurretWithJoystickCommand extends BaseCommand {

    final TurretRotationSubsystem rotationSubsystem;
    final OperatorInterface oi;

    @Inject
    public RotateTurretWithJoystickCommand(OperatorInterface oi, TurretRotationSubsystem rotationSubsystem) {
        this.oi = oi;
        this.rotationSubsystem = rotationSubsystem;
        this.requires(this.rotationSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        rotationSubsystem.setRotationPower(oi.leftJoystick.getButton(8) ? oi.leftJoystick.getVector().x : 0);
    }
}
