package competition.subsystems.feeder.commands;

import com.google.inject.Inject;

import competition.subsystems.feeder.FeederSubsystem;
import xbot.common.command.BaseCommand;

public class LowerFeederCommand extends BaseCommand{

    final FeederSubsystem feederSubsystem;
    
    @Inject
    public LowerFeederCommand(FeederSubsystem feederSubsystem){
        this.feederSubsystem = feederSubsystem;
        this.requires(this.feederSubsystem);
    }
    
    @Override
    public void initialize() {
        feederSubsystem.lower();
    }

    @Override
    public void execute() {
        
    }
    
}
