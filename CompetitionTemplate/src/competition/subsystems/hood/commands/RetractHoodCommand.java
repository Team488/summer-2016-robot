package competition.subsystems.hood.commands;

import com.google.inject.Inject;

import competition.subsystems.hood.HoodSubsystem;
import xbot.common.command.BaseCommand;

public class RetractHoodCommand extends BaseCommand{
    
    final HoodSubsystem hoodSubsystem;
    
    @Inject
    public RetractHoodCommand(HoodSubsystem hoodSubsystem){
        this.hoodSubsystem = hoodSubsystem;
        this.requires(this.hoodSubsystem);
    }
    
    @Override
    public void initialize() {
        hoodSubsystem.retract();
    }

    @Override
    public void execute() {
        
    }

}

