package competition.subsystems.feeder.commands.RaiseFeederCommand;

import com.google.inject.Inject;

import competition.subsystems.FeederSubsystem.FeederSubsystem;
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        feederSubsystem.raise();
    }
    
}
