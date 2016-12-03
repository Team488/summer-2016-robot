package competition.subsystems.turret.rotation.commands;

import com.google.inject.Inject;

import competition.operator_interface.OperatorInterface;
import competition.subsystems.turret.rotation.TurretRotationSubsystem;
import xbot.common.command.BaseCommand;
import xbot.common.controls.sensors.XJoystick;

public class RotateTurretWithGamepadCommand extends BaseCommand {

    final TurretRotationSubsystem rotationSubsystem;
    
    final XJoystick leftTrigger;
    final XJoystick rightTrigger;

    @Inject
    public RotateTurretWithGamepadCommand(OperatorInterface oi, TurretRotationSubsystem rotationSubsystem) {
        this.leftTrigger = oi.gamepad.getLeftTrigger();
        this.rightTrigger = oi.gamepad.getRightTrigger();
        this.rotationSubsystem = rotationSubsystem;
        this.requires(this.rotationSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double leftIntent = leftTrigger.getVector().y;
        double rightIntent = -1 * rightTrigger.getVector().y;
        
        if(rightIntent != 0 && leftIntent != 0){
            rotationSubsystem.setRotationPower(0);
        } else {
            rotationSubsystem.setRotationPower(leftIntent + rightIntent);
        }
    }
}
