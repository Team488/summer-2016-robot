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
public class HoodSubsystem extends BaseSubsystem{
    private static Logger log = Logger.getLogger(HoodSubsystem.class);
    
    private final XCANTalon hoodMotor;
    
    DoubleProperty propExtendPower;
    DoubleProperty propRetractPower;
    
    @Inject
    public HoodSubsystem(WPIFactory factory, XPropertyManager propManager){
        hoodMotor = factory.getCANTalonSpeedController(0);
        
        hoodMotor.setBrakeEnableDuringNeutral(true);

        hoodMotor.configNominalOutputVoltage(0, -0);
        hoodMotor.configPeakOutputVoltage(12, -12);

        hoodMotor.setProfile(0);
        hoodMotor.setControlMode(TalonControlMode.PercentVbus);
        
        propExtendPower = propManager.createPersistentProperty("Hood Extend Power", 1);
        propRetractPower = propManager.createPersistentProperty("Hood Retract Power", -1);
    }
    
    public void extend(){
        hoodMotor.set(propExtendPower.get());
    }
    
    public void retract(){
        hoodMotor.set(propRetractPower.get());
    }
}

