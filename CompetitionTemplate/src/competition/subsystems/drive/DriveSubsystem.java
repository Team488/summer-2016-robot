package competition.subsystems.drive;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import xbot.common.command.BaseSubsystem;
import xbot.common.controls.actuators.XSpeedController;
import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.properties.PropertyManager;

@Singleton
public class DriveSubsystem extends BaseSubsystem {
	
    private static Logger log = Logger.getLogger(DriveSubsystem.class);
    
    public final XSpeedController leftFrontDrive; 
    public final XSpeedController leftRearDrive; 
    public final XSpeedController rightFrontDrive; 
    public final XSpeedController rightRearDrive; 
	
	@Inject
    public DriveSubsystem(WPIFactory factory, PropertyManager propManager)
    {
        log.info("Creating DriveSubsystem");

        this.leftFrontDrive = factory.getSpeedController(0);
        this.leftRearDrive = factory.getSpeedController(2);
        
        this.rightFrontDrive = factory.getSpeedController(1);
        this.rightFrontDrive.setInverted(true);
        this.rightRearDrive = factory.getSpeedController(3);
        this.rightRearDrive.setInverted(true);
    }
	
	public void tankDrive(double leftPower, double rightPower) {
		this.leftFrontDrive.set(leftPower);
		this.leftRearDrive.set(leftPower);
		this.rightFrontDrive.set(rightPower);
		this.rightRearDrive.set(rightPower);
	}
}
