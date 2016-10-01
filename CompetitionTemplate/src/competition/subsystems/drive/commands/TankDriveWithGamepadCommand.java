package competition.subsystems.drive.commands;

import com.google.inject.Inject;

import competition.operator_interface.OperatorInterface;
import competition.subsystems.drive.DriveSubsystem;
import xbot.common.command.BaseCommand;

public class TankDriveWithGamepadCommand extends BaseCommand {

    final DriveSubsystem driveSubsystem;
    final OperatorInterface oi;

    @Inject
    public TankDriveWithGamepadCommand(OperatorInterface oi, DriveSubsystem driveSubsystem) {
        this.oi = oi;
        this.driveSubsystem = driveSubsystem;
        this.requires(this.driveSubsystem);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        // TODO: replace this with proper logic
        if(!oi.leftJoystick.getButton(8)) {
            driveSubsystem.tankDrive(oi.leftJoystick.getVector().y, oi.leftJoystick.getRawAxis(5));
        }
        else {
            driveSubsystem.tankDrive(0, 0);
        }
    }
}
