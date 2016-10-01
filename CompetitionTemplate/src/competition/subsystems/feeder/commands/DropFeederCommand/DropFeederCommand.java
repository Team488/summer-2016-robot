package competition.subsystems.feeder.commands.DropFeederCommand;

import com.google.inject.Inject;

import competition.subsystems.FeederSubsystem.FeederSubsystem;
import xbot.common.command.BaseCommand;

public class DropFeederCommand extends BaseCommand{

    final FeederSubsystem feederSubsystem;
    
    @Inject
    public DropFeederCommand(FeederSubsystem feederSubsystem){
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
        feederSubsystem.drop();
    }
    
}
