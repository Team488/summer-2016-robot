package competition.subsystems.collector;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import competition.subsystems.drive.DriveSubsystem;
import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XCANTalon;
import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.properties.XPropertyManager;

@Singleton
public class CollectorSubsystem extends BaseSubsystem {
    private static Logger log = Logger.getLogger(CollectorSubsystem.class);

    public final XCANTalon collectorMotor;
    
    @Inject
    public CollectorSubsystem(WPIFactory factory, XPropertyManager propManager){
        collectorMotor = factory.getCANTalonSpeedController(5);
    }
    
    public void stop(){
        collectorMotor.set(0);
    }
    
    public void intake(){
        collectorMotor.set(0.5);
    }
    
    public void eject(){
        collectorMotor.set(-0.5);
    }
}

