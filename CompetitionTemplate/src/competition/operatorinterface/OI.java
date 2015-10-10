package competition.operatorinterface;

import com.google.inject.Inject;

import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.wpi_extensions.mechanism_wrappers.XJoystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public XJoystick leftJoystick;
	public XJoystick rightJoystick;
	
	@Inject
	public OI(WPIFactory factory) {
		leftJoystick = factory.getJoystick(1);
		rightJoystick = factory.getJoystick(2);

        leftJoystick.setYInversion(true);
        rightJoystick.setXInversion(true);
	} 
}
