package competition.subsystems.drive;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.math.MathUtils;
import xbot.common.properties.DoubleProperty;
import xbot.common.properties.XPropertyManager;

@Singleton
public class DriveSubsystem extends BaseSubsystem {
    private static Logger log = Logger.getLogger(DriveSubsystem.class);

    public final XCANTalon leftDrive;
    public final XCANTalon leftDriveSlave;
    public final XCANTalon rightDrive;
    public final XCANTalon rightDriveSlave;
    
    private final DoubleProperty encoderCodesProperty;
    private final DoubleProperty maxSpeedProperty;
    private final DoubleProperty p;
    private final DoubleProperty i;
    private final DoubleProperty d;
    private final DoubleProperty f;

    @Inject
    public DriveSubsystem(WPIFactory factory, XPropertyManager propManager) {
        log.info("Creating DriveSubsystem");

        // TODO: Update these defaults. The current values are blind guesses.
        encoderCodesProperty = propManager.createPersistentProperty("Drive encoder codes per rev", 512);
        maxSpeedProperty = propManager.createPersistentProperty("Max drive motor speed (rotations per second)", 5);

        p = propManager.createPersistentProperty("Drive P", 2);
        i = propManager.createPersistentProperty("Drive I", 0);
        d = propManager.createPersistentProperty("Drive D", -100);
        f = propManager.createPersistentProperty("Drive F", 0);
        
        this.leftDrive = factory.getCANTalonSpeedController(3);
        this.leftDriveSlave = factory.getCANTalonSpeedController(4);
        configMotorTeam(leftDrive, leftDriveSlave);
        leftDrive.createTelemetryProperties("Left master", propManager);
        leftDriveSlave.createTelemetryProperties("Left slave", propManager);
        
        this.rightDrive = factory.getCANTalonSpeedController(1);
        this.rightDriveSlave = factory.getCANTalonSpeedController(2);
        configMotorTeam(rightDrive, rightDriveSlave);
        rightDrive.createTelemetryProperties("Right master", propManager);
        rightDriveSlave.createTelemetryProperties("Right slave", propManager);
    }

    private void configMotorTeam(XCANTalon master, XCANTalon slave) {
        // TODO: Check faults and voltage/temp/current
        
        // Master config
        master.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        master.setBrakeEnableDuringNeutral(false);
        master.reverseSensor(false);
        master.enableLimitSwitches(false, false);
        
        master.configNominalOutputVoltage(0,  -0);
        master.configPeakOutputVoltage(12, -12);

        master.setProfile(0);
        master.setF(0);
        updateMotorConfig(master);
        master.setControlMode(TalonControlMode.Speed);
        //master.setControlMode(TalonControlMode.PercentVbus);
        
        master.set(0);
        
        // Slave config
        slave.configNominalOutputVoltage(0,  -0);
        slave.configPeakOutputVoltage(12, -12);
        slave.enableLimitSwitches(false, false);
        
        slave.setControlMode(TalonControlMode.Follower);
        slave.set(master.getDeviceID());
    }
    
    private void updateMotorConfig(XCANTalon motor) {
        motor.configEncoderCodesPerRev((int)encoderCodesProperty.get());
        motor.setP(p.get());
        motor.setI(i.get());
        motor.setD(d.get());
        motor.setF(f.get());
    }
    
    public double convertPowerToVelocityTarget(double power) {
        double maxTicksPerTenMs = maxSpeedProperty.get() * encoderCodesProperty.get() / 100;
        double velocityTarget = power * maxTicksPerTenMs;
        return velocityTarget;
    }
    
    private void ensureSpeedModeForTalon(XCANTalon talon) {
        if (talon.getControlMode() != TalonControlMode.Speed) {
            talon.setControlMode(TalonControlMode.Speed);
        }
    }
    
    private void ensureSpeedModeForDrive() {
        ensureSpeedModeForTalon(leftDrive);
        ensureSpeedModeForTalon(rightDrive);
    }

    public void tankDriveVelocityPid(double leftPower, double rightPower) {
        // TODO: Move parameter updates to something more consistent
        ensureSpeedModeForDrive();
        
        // Coerce powers into appropriate limits
        leftPower = MathUtils.constrainDouble(leftPower, -1, 1);
        rightPower = MathUtils.constrainDouble(rightPower, -1, 1);
        
        updateMotorConfig(leftDrive);
        updateMotorConfig(rightDrive);

        leftDrive.set(convertPowerToVelocityTarget(leftPower));
        rightDrive.set(convertPowerToVelocityTarget(rightPower));

        //leftDrive.set(leftPower);
        //rightDrive.set(rightPower);
        
        leftDrive.updateTelemetryProperties();
        leftDriveSlave.updateTelemetryProperties();
        rightDrive.updateTelemetryProperties();
        rightDriveSlave.updateTelemetryProperties();
    }
}
