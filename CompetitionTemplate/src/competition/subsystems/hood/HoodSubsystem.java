package competition.subsystems.hood;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.XPropertyManager;

@Singleton
public class HoodSubsystem extends BaseSubsystem {
    private static Logger log = Logger.getLogger(HoodSubsystem.class);

    private final XCANTalon hoodMotor;

    DoubleProperty propExtendPower;
    DoubleProperty propRetractPower;
    DoubleProperty propStopPower;

    DoubleProperty hoodPosition;
    DoubleProperty hoodExtendPosition;
    DoubleProperty hoodRetractPosition;

    @Inject
    public HoodSubsystem(WPIFactory factory, XPropertyManager propManager) {
        hoodMotor = factory.getCANTalonSpeedController(9);

        hoodMotor.setBrakeEnableDuringNeutral(true);

        hoodMotor.configNominalOutputVoltage(0, -0);
        hoodMotor.configPeakOutputVoltage(12, -12);

        hoodMotor.setProfile(0);
        hoodMotor.setControlMode(TalonControlMode.PercentVbus);
        hoodMotor.setInverted(true);

        propExtendPower = propManager.createPersistentProperty("Hood Extend Power", 1);
        propRetractPower = propManager.createPersistentProperty("Hood Retract Power", -1);
        propStopPower = propManager.createPersistentProperty("Hood Power Power", 0);
    }

    public void extend() {
        hoodMotor.set(propExtendPower.get());
    }

    public void retract() {
        hoodMotor.set(propRetractPower.get());
    }

    public void stop() {
        hoodMotor.set(propStopPower.get());
    }

    public void setRotationPower(double power) {
        hoodPosition.set(hoodMotor.getEncoderPosition());

        if (hoodPosition.get() > hoodExtendPosition.get()
                || hoodPosition.get() < hoodRetractPosition.get()) {
            setRotationPower(0);
            return;
        }

        hoodMotor.set(power);
    }
}
