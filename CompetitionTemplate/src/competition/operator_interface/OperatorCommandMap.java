package competition.operator_interface;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.collector.commands.IntakeCollectorCommand;
import competition.subsystems.collector.commands.EjectCollectorCommand;
import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import competition.subsystems.turret.shooter_wheel.commands.StartShooterWheelCommand;
import competition.subsystems.turret.shooter_wheel.commands.StopShooterWheelCommand;

@Singleton
public class OperatorCommandMap {
    // For mapping operator interface buttons to commands
    
    @Inject
    public void setupCollectorCommands(OperatorInterface oi, IntakeCollectorCommand ccc, EjectCollectorCommand ecc){
        oi.leftButtons.getifAvailable(1).whileHeld(ccc);
        oi.leftButtons.getifAvailable(2).whileHeld(ecc);
    }
    
    @Inject
    public void setupShooterWheelCommands(OperatorInterface operatorInterface, 
            StartShooterWheelCommand startShooterWheelCommand,
            StopShooterWheelCommand stopShooterWheelCommand) {
        startShooterWheelCommand.includeOnSmartDashboard();
        stopShooterWheelCommand.includeOnSmartDashboard();
    }
}
