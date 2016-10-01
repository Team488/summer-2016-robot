package competition.subsystems.turret.shooter_wheel;

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
public class ShooterWheelSubsystem extends BaseSubsystem {
    private static Logger log = Logger.getLogger(ShooterWheelSubsystem.class);

    private final XCANTalon masterMotor;
    private final XCANTalon slaveMotor;
    
    private final DoubleProperty shooterLaunchSpeed;
    
    // output telemetry properties
    private final DoubleProperty shooterCurrentSpeed;
    private final DoubleProperty shooterTargetSpeedProp;
    private final DoubleProperty shooterOutputPower;
    private final DoubleProperty shooterTalonError;
    private final BooleanProperty atSpeedProp;
    
    private final DoubleProperty encoderCodesProperty;
    private final DoubleProperty p;
    private final DoubleProperty i;
    private final DoubleProperty d;
    private final DoubleProperty f;
    
    private double shooterTargetSpeed = 0;
    private final DoubleProperty nominalSpeedThresh;

    @Inject
    public ShooterWheelSubsystem(WPIFactory factory, XPropertyManager propManager) {
        log.info("Creating ShooterWheelSubsystem");

        // TODO: Update these defaults. The current values are blind guesses.
        shooterLaunchSpeed = propManager.createPersistentProperty("Shooter wheel launch speed", 10);
        
        encoderCodesProperty = propManager.createPersistentProperty("Shooter encoder codes per rev", 4096);

        p = propManager.createPersistentProperty("Shooter wheel P", 10);
        i = propManager.createPersistentProperty("Shooter wheel I", 0);
        d = propManager.createPersistentProperty("Shooter wheel D", 0);
        f = propManager.createPersistentProperty("Shooter wheel F", 0);
        
        nominalSpeedThresh = propManager.createPersistentProperty("Shooter wheel nominal speed thresh (rot per sec)", 1);
        shooterCurrentSpeed = propManager.createEphemeralProperty("Shooter wheel current speed (rot per sec)", 0);
        shooterTargetSpeedProp = propManager.createEphemeralProperty("Shooter wheel target speed (rot per sec)", 0);
        shooterOutputPower = propManager.createEphemeralProperty("Shooter wheel current output power", 0);
        atSpeedProp = propManager.createEphemeralProperty("Is shooter at speed?", false);
        shooterTalonError = propManager.createEphemeralProperty("Shooter talon error", 0);
        
        this.masterMotor = factory.getCANTalonSpeedController(7);
        this.slaveMotor = factory.getCANTalonSpeedController(8);
        configMotorTeam(masterMotor, slaveMotor);
        masterMotor.createTelemetryProperties("Shooter master", propManager);
        slaveMotor.createTelemetryProperties("Shooter slave", propManager);
    }

    private void configMotorTeam(XCANTalon master, XCANTalon slave) {
        // TODO: Check faults and voltage/temp/current
        
        // Master config
        master.setFeedbackDevice(FeedbackDevice.QuadEncoder);
        master.setBrakeEnableDuringNeutral(false);
        // TODO: move inversion
        //master.reverseSensor(true);
        master.setInverted(true);
        
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

    public void setShooterTarget(double shooterTargetSpeed) {
        this.shooterTargetSpeed = shooterTargetSpeed;
        shooterTargetSpeedProp.set(shooterTargetSpeed);
        masterMotor.set(shooterTargetSpeed * 60);
        
        updateMotorState();
    }
    
    public void updateMotorState() {
        // TODO: Move parameter updates to something more consistent
        updateMotorConfig(masterMotor);
        
        masterMotor.updateTelemetryProperties();
        slaveMotor.updateTelemetryProperties();
        
        atSpeedProp.set(isAtSpeed());
    }
    
    public double getSpeed() {
        return masterMotor.getSpeed() * 10 / encoderCodesProperty.get();
    }
    
    public boolean isAtSpeed() {
        return Math.abs(getSpeed() - shooterTargetSpeed) <= nominalSpeedThresh.get();
    }
    
    public double getShooterLaunchSpeed() {
        return shooterLaunchSpeed.get();
    }
    
    public void updateTelemetry() {
        shooterCurrentSpeed.set(getSpeed());
        shooterOutputPower.set(masterMotor.getOutputVoltage() / masterMotor.getBusVoltage());
        shooterTalonError.set(masterMotor.getClosedLoopError());
    }
}
