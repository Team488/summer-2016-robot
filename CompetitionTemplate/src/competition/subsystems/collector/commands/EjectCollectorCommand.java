package competition.subsystems.collector.commands;

import com.google.inject.Inject;

import competition.subsystems.collector.CollectorSubsystem;
import xbot.common.command.BaseCommand;

public class EjectCollectorCommand extends BaseCommand {

    final CollectorSubsystem collectorSubsystem;

    @Inject
    public EjectCollectorCommand(CollectorSubsystem collectorSubsystem) {
        this.collectorSubsystem = collectorSubsystem;
        this.requires(this.collectorSubsystem);
    }

    @Override
    public void initialize() {
        collectorSubsystem.eject();
    }

    @Override
    public void execute() {

    }
}