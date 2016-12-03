package competition.subsystems.turret.rotation;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.properties.BooleanProperty;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.XPropertyManager;

@Singleton
public class TurretRotationSubsystem extends BaseSubsystem {
    private static Logger log = Logger.getLogger(TurretRotationSubsystem.class);

    public final XCANTalon rotationMotor;
    
    final DoubleProperty turretPosition;
    
    final DoubleProperty turretPositivePositionLimit;
    final DoubleProperty turretNegativePositionLimit;

    @Inject
    public TurretRotationSubsystem(WPIFactory factory, XPropertyManager propManager) {
        log.info("Creating TurretRotationSubsystem");

        rotationMotor = factory.getCANTalonSpeedController(6);
        
        rotationMotor.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        rotationMotor.setBrakeEnableDuringNeutral(false);
        rotationMotor.reverseSensor(false);
        
        rotationMotor.configNominalOutputVoltage(0,  -0);
        rotationMotor.configPeakOutputVoltage(12, -12);

        rotationMotor.setProfile(0);
        rotationMotor.setControlMode(TalonControlMode.PercentVbus);
        
        rotationMotor.enableLimitSwitches(false, false);
        
        rotationMotor.createTelemetryProperties("turretRotationMotor", propManager);
        
        turretPosition = propManager.createEphemeralProperty("turretPosition", 0);
        
        turretPositivePositionLimit = propManager.createPersistentProperty("turretPositivePositionLimit", 2000);
        turretNegativePositionLimit = propManager.createPersistentProperty("turretNegativePositionLimit", -2000);
    }

    public void setRotationPower(double power) {
        turretPosition.set(rotationMotor.getEncoderPosition());

        if(turretPosition.get() > turretPositivePositionLimit.get()
                || turretPosition.get() < turretNegativePositionLimit.get()) {
            rotationMotor.set(0);
            return;
        }
        
        rotationMotor.set(power);
    }
}
