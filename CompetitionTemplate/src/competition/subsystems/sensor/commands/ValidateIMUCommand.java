package competition.subsystems.sensor.commands;

import com.google.inject.Inject;

import competition.subsystems.sensor.ValidateIMUSubsystem;
import xbot.common.command.BaseCommand;

/**
 *
 */
public class ValidateIMUCommand extends BaseCommand {

    ValidateIMUSubsystem validateIMUSubsystem; 
    
    @Inject
    public ValidateIMUCommand(ValidateIMUSubsystem validateIMUSubsystem) {
        this.validateIMUSubsystem = validateIMUSubsystem;      
        this.requires(this.validateIMUSubsystem);
    }
    

    // Called just before this Command runs the first time
    @Override
    public void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    public void execute() {
        validateIMUSubsystem.updateDashboard(); 
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
