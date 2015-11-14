package competition.subsystems.sensor.commands;

import com.google.inject.Inject;

import competition.operatorinterface.OI;
import competition.subsystems.drive.DriveSubsystem;
import competition.subsystems.sensor.SensorSubsystem;
import edu.wpi.first.wpilibj.Gyro;
import xbot.common.command.BaseCommand;

/**
 *
 */
public class GetGyroPropertiesCommand extends BaseCommand {

    SensorSubsystem sensorSubsystem; 
    
    @Inject
    public GetGyroPropertiesCommand(SensorSubsystem sensorSubsystem) {
        this.sensorSubsystem = sensorSubsystem;      
        this.requires(this.sensorSubsystem);
    }
    

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        sensorSubsystem.operatorControl();
        
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    public boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    public void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    public void interrupted() {
    }
}
