package competition.operator_interface;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.wpi_extensions.mechanism_wrappers.XJoystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
@Singleton
public class OperatorInterface {
	public XJoystick leftJoystick;
	public XJoystick rightJoystick;
	
	public JoystickButtonManager leftButtons; 
	
	@Inject
	public OperatorInterface(WPIFactory factory) {
		leftJoystick = factory.getJoystick(1);
		rightJoystick = factory.getJoystick(2);

        leftJoystick.setYInversion(true);
        rightJoystick.setXInversion(true);
	} 
}

