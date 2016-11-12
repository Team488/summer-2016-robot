package competition.subsystems.turret.shooter_wheel.commands;

import org.apache.log4j.Logger;

import com.google.inject.Inject;

import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import xbot.common.command.BaseCommand;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.XPropertyManager;

public class MeasureShooterWheelCommand extends BaseCommand {
    static Logger log = Logger.getLogger(StartShooterWheelCommand.class);
    
    final ShooterWheelSubsystem shooterSubsystem;
    
    final DoubleProperty minRecordedWheelSpeed;
    final DoubleProperty maxRecordedWheelSpeed;
    final DoubleProperty diffRecordedWheelSpeed;

    @Inject
    public MeasureShooterWheelCommand(ShooterWheelSubsystem shooterSubsystem, XPropertyManager propManager) {
        this.shooterSubsystem = shooterSubsystem;
        
        minRecordedWheelSpeed = propManager.createEphemeralProperty("MinRecordedWheelSpeed", 999999999999.0);
        maxRecordedWheelSpeed = propManager.createEphemeralProperty("MaxRecordedWheelSpeed", 0.0);
        diffRecordedWheelSpeed = propManager.createEphemeralProperty("Difference in WheelSpeed", 0.0);
    }

    @Override
    public void initialize() {
        minRecordedWheelSpeed.set(999999999999.0);
        maxRecordedWheelSpeed.set(0);
        diffRecordedWheelSpeed.set(0);
    }

    @Override
    public void execute() {
        double currentSpeed = shooterSubsystem.getSpeed();
        
        if (currentSpeed > maxRecordedWheelSpeed.get()) {
            maxRecordedWheelSpeed.set(currentSpeed);
        }
        
        if (currentSpeed < minRecordedWheelSpeed.get()) {
            minRecordedWheelSpeed.set(currentSpeed);
        }
        
        diffRecordedWheelSpeed.set(maxRecordedWheelSpeed.get() - minRecordedWheelSpeed.get());
    }
    
    @Override
    public boolean isFinished() {
        return false;
    }
}
