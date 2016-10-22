package competition.subsystems.collector;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.drive.DriveSubsystem;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.XPropertyManager;

@Singleton
public class CollectorSubsystem extends BaseSubsystem {
    private static Logger log = Logger.getLogger(CollectorSubsystem.class);

    private final DoubleProperty intakeSpeedProp;
    private final DoubleProperty ejectSpeedProp;
    private final XCANTalon collectorMotor;

    @Inject
    public CollectorSubsystem(WPIFactory factory, XPropertyManager propManager) {
        collectorMotor = factory.getCANTalonSpeedController(5);

        collectorMotor.setBrakeEnableDuringNeutral(false);

        collectorMotor.configNominalOutputVoltage(0, -0);
        collectorMotor.configPeakOutputVoltage(12, -12);

        collectorMotor.setProfile(0);
        collectorMotor.setControlMode(TalonControlMode.PercentVbus);

        intakeSpeedProp = propManager.createPersistentProperty("Collector intake speed", 0.5);
        ejectSpeedProp = propManager.createPersistentProperty("Collector eject speed", -0.5);
    }

    public void stop() {
        collectorMotor.set(0);
    }

    public void intake() {
        collectorMotor.set(intakeSpeedProp.get());
    }

    public void eject() {
        collectorMotor.set(ejectSpeedProp.get());
    }
}
