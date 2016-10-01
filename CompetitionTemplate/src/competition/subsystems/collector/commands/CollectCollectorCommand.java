package competition.subsystems.collector.commands;

import com.google.inject.Inject;

import competition.subsystems.collector.CollectorSubsystem;
import xbot.common.command.BaseCommand;

public class CollectCollectorCommand extends BaseCommand{
    
    final CollectorSubsystem collectSubsystem;
    
    @Inject
    public CollectCollectorCommand(CollectorSubsystem collectSubsystem){
        this.collectSubsystem = collectSubsystem;
        this.requires(this.collectSubsystem);
    }
    
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        collectSubsystem.intake();
    }

}

