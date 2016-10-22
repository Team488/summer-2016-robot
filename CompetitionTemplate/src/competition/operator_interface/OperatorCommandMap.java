package competition.operator_interface;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.collector.commands.IntakeCollectorCommand;
import competition.subsystems.collector.commands.EjectCollectorCommand;
import competition.subsystems.feeder.commands.RaiseFeederCommand;
import competition.subsystems.turret.shooter_wheel.commands.StartShooterWheelCommand;
import competition.subsystems.turret.shooter_wheel.commands.StopShooterWheelCommand;

@Singleton
public class OperatorCommandMap {
    // For mapping operator interface buttons to commands
    
    @Inject
    public void setupCollectorCommands(OperatorInterface oi, IntakeCollectorCommand intakeCommand, EjectCollectorCommand ejectCommand){
        oi.leftButtons.getifAvailable(1).whileHeld(intakeCommand);
        oi.leftButtons.getifAvailable(3).whileHeld(ejectCommand);
    }
    
    @Inject
    public void setupShooterWheelCommands(OperatorInterface operatorInterface, 
            StartShooterWheelCommand startShooterWheelCommand,
            StopShooterWheelCommand stopShooterWheelCommand) {
        startShooterWheelCommand.includeOnSmartDashboard();
        stopShooterWheelCommand.includeOnSmartDashboard();
    }
    
    @Inject
    public void setupFeederCommands(OperatorInterface operatorInterface, 
            RaiseFeederCommand raiseFeederCommand) {
        raiseFeederCommand.includeOnSmartDashboard();
        operatorInterface.leftButtons.getifAvailable(6).whileHeld(raiseFeederCommand);
    }
}
