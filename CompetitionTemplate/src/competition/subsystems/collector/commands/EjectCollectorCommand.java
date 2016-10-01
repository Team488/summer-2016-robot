package competition.subsystems.collector.commands;

import com.google.inject.Inject;

import competition.subsystems.collector.CollectorSubsystem;
import xbot.common.command.BaseCommand;

public class EjectCollectorCommand extends BaseCommand{
    
    final CollectorSubsystem ejectSubsystem;
    
    @Inject
    public EjectCollectorCommand(CollectorSubsystem ejectSubsystem){
        this.ejectSubsystem = ejectSubsystem;
        this.requires(this.ejectSubsystem);
    }
    
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void execute() {
        // TODO Auto-generated method stub
        ejectSubsystem.eject();
    }

}

