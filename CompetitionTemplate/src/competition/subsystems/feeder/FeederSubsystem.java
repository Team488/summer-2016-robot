package competition.subsystems.feeder;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XSolenoid;
import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.properties.XPropertyManager;

@Singleton
public class FeederSubsystem extends BaseSubsystem{
    private static Logger log = Logger.getLogger(FeederSubsystem.class);

    public final XSolenoid feederSolenoid;
    
    @Inject
    public FeederSubsystem(WPIFactory factory, XPropertyManager propManager){
        feederSolenoid = factory.getSolenoid(0);
    }
    
    public void raise(){
        feederSolenoid.set(true);
    }
    
    public void lower(){
        feederSolenoid.set(false);
    }
}
