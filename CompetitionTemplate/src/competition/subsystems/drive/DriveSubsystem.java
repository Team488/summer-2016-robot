package competition.subsystems.drive;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.WPIFactory;
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

    @Inject
    public DriveSubsystem(WPIFactory factory, XPropertyManager propManager) {
        log.info("Creating DriveSubsystem");

        // TODO: Update these defaults. The current values are blind guesses.
        encoderCodesProperty = propManager.createPersistentProperty("Drive encoder codes per rev", 1000);
        maxSpeedProperty = propManager.createPersistentProperty("Max drive motor speed", 5000);

        p = propManager.createPersistentProperty("Drive P", 10);
        i = propManager.createPersistentProperty("Drive I", 0);
        d = propManager.createPersistentProperty("Drive D", 0);
        
        this.leftDrive = factory.getCANTalonSpeedController(1);
        this.leftDriveSlave = factory.getCANTalonSpeedController(2);
        configMotorTeam(leftDrive, leftDriveSlave);
        leftDrive.createTelemetryProperties("Left master", propManager);
        leftDriveSlave.createTelemetryProperties("Left slave", propManager);
        
        this.rightDrive = factory.getCANTalonSpeedController(3);
        this.rightDriveSlave = factory.getCANTalonSpeedController(4);
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
        
        master.configNominalOutputVoltage(0,  -0);
        master.configPeakOutputVoltage(12, -12);
        
        master.setProfile(0);
        master.setF(0);
        updateMotorConfig(master);
        master.setControlMode(TalonControlMode.Speed);
        
        master.set(0);
        
        // Slave config
        slave.configNominalOutputVoltage(0,  -0);
        slave.configPeakOutputVoltage(12, -12);
        
        slave.setControlMode(TalonControlMode.Follower);
        slave.set(master.getDeviceID());
    }
    
    private void updateMotorConfig(XCANTalon motor) {
        motor.configEncoderCodesPerRev((int)encoderCodesProperty.get());
        motor.setP(p.get());
        motor.setI(i.get());
        motor.setD(d.get());
    }
    
    public void tankDrive(double leftPower, double rightPower) {
        // TODO: Move parameter updates to something more consistent
        
        updateMotorConfig(leftDrive);
        updateMotorConfig(rightDrive);

        leftDrive.set(leftPower * maxSpeedProperty.get());
        rightDrive.set(rightPower * maxSpeedProperty.get());
        
        leftDrive.updateTelemetryProperties();
        leftDriveSlave.updateTelemetryProperties();
        rightDrive.updateTelemetryProperties();
        rightDriveSlave.updateTelemetryProperties();
    }
}
