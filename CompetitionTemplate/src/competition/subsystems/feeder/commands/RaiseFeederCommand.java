package competition.subsystems.feeder.commands;

import com.google.inject.Inject;

import competition.subsystems.feeder.FeederSubsystem;
import xbot.common.command.BaseCommand;

public class RaiseFeederCommand extends BaseCommand{

    final FeederSubsystem feederSubsystem;
    
    @Inject
    public RaiseFeederCommand(FeederSubsystem feederSubsystem){
        this.feederSubsystem = feederSubsystem;
        this.requires(this.feederSubsystem);
    }
    
    @Override
    public void initialize() {
        feederSubsystem.raise();
    }

    @Override
    public void execute() {
        
    }
    
}
