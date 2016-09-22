package competition.subsystems.turret.shooter_wheel.commands;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import xbot.common.command.BaseCommand;

public class StartShooterWheelCommand extends BaseCommand {
    static Logger log = Logger.getLogger(StartShooterWheelCommand.class);
    
    final ShooterWheelSubsystem shooterSubsystem;

    @Inject
    public StartShooterWheelCommand(ShooterWheelSubsystem shooterSubsystem) {
        this.shooterSubsystem = shooterSubsystem;
        this.requires(this.shooterSubsystem);
    }

    @Override
    public void initialize() {
        log.info("Starting shooter wheel");
        shooterSubsystem.setShooterTarget(shooterSubsystem.getShooterLaunchSpeed());
    }

    @Override
    public void execute() {
        
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }
}
