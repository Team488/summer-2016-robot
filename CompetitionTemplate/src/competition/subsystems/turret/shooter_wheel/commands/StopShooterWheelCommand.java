package competition.subsystems.turret.shooter_wheel.commands;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import xbot.common.command.BaseCommand;

public class StopShooterWheelCommand extends BaseCommand {
    static Logger log = Logger.getLogger(StopShooterWheelCommand.class);
    
    final ShooterWheelSubsystem shooterSubsystem;

    @Inject
    public StopShooterWheelCommand(ShooterWheelSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.requires(this.shooterSubsystem);
    }

    @Override
    public void initialize() {
        log.info("Stopping shooter wheel");
        shooterSubsystem.setShooterTarget(0);
    }

    @Override
    public void execute() {
        
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}
