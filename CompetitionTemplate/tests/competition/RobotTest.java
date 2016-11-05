package competition;

import org.junit.Test;

import competition.operator_interface.OperatorCommandMap;
import competition.subsystems.SubsystemDefaultCommandMap;
import competition.subsystems.turret.rotation.TurretRotationSubsystem;
import competition.subsystems.turret.shooter_wheel.ShooterWheelSubsystem;
import xbot.common.injection.BaseWPITest;

public class RobotTest extends BaseWPITest{
    
    @Test
    public void testSubsystemInitialization() {
        this.injector.getInstance(SubsystemDefaultCommandMap.class);
        this.injector.getInstance(OperatorCommandMap.class);
        this.injector.getInstance(TurretRotationSubsystem.class);
        this.injector.getInstance(ShooterWheelSubsystem.class);
    }
    
}
