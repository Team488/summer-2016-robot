package competition.subsystems.collector.commands;

import com.google.inject.Inject;

import competition.subsystems.collector.CollectorSubsystem;
import xbot.common.command.BaseCommand;

public class IntakeCollectorCommand extends BaseCommand {
    final CollectorSubsystem collectorSubsystem;

    @Inject
    public IntakeCollectorCommand(CollectorSubsystem collectorSubsystem) {
        this.collectorSubsystem = collectorSubsystem;
        this.requires(this.collectorSubsystem);
    }

    @Override
    public void initialize() {
        collectorSubsystem.intake();
    }

    @Override
    public void execute() {
        
    }
}