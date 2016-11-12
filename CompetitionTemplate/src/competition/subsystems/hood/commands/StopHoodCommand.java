package competition.subsystems.hood.commands;

import com.google.inject.Inject;

import competition.subsystems.hood.HoodSubsystem;
import xbot.common.command.BaseCommand;

public class StopHoodCommand extends BaseCommand{
    final HoodSubsystem hoodSubsystem;
    
    @Inject
    public StopHoodCommand(HoodSubsystem hoodSubsystem){
        this.hoodSubsystem = hoodSubsystem;
        this.requires(this.hoodSubsystem);
    }
    
    @Override
    public void initialize() {
        hoodSubsystem.stop();
    }

    @Override
    public void execute() {
        
    }

}

