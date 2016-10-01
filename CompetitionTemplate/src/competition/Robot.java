
package competition;

import competition.operator_interface.OperatorCommandMap;
import competition.subsystems.SubsystemDefaultCommandMap;
import competition.subsystems.turret.rotation.TurretRotationSubsystem;
import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import xbot.common.command.BaseRobot;

public class Robot extends BaseRobot {

    @Override
    protected void initializeSystems() {
        super.initializeSystems();
        this.injector.getInstance(SubsystemDefaultCommandMap.class);
        this.injector.getInstance(OperatorCommandMap.class);
        this.injector.getInstance(TurretRotationSubsystem.class);
        this.injector.getInstance(ShooterWheelSubsystem.class);
    }
}
