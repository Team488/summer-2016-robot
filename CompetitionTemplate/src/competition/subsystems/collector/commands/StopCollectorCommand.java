package competition.subsystems.collector.commands;

import com.google.inject.Inject;

import competition.subsystems.collector.CollectorSubsystem;
import xbot.common.command.BaseCommand;

public class StopCollectorCommand extends BaseCommand{
    
    final CollectorSubsystem collectorSubsystem;
    
    @Inject
    public StopCollectorCommand(CollectorSubsystem collectorSubsystem){
        this.collectorSubsystem = collectorSubsystem;
        this.requires(this.collectorSubsystem);
    }
    
    @Override
    public void initialize() {
        collectorSubsystem.stop();
    }

    @Override
    public void execute() {
    }

}
