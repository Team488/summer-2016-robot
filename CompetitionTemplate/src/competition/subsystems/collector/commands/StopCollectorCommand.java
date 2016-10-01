package competition.subsystems.collector.commands;

import com.google.inject.Inject;

import competition.subsystems.collector.CollectorSubsystem;
import xbot.common.command.BaseCommand;

public class StopCollectorCommand extends BaseCommand{
    
    final CollectorSubsystem CollectorSubsystem;
    
    @Inject
    public StopCollectorCommand(CollectorSubsystem CollectorSubsystem){
        this.CollectorSubsystem = CollectorSubsystem;
        this.requires(this.CollectorSubsystem);
    }
    
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        CollectorSubsystem.stop();
    }

}
