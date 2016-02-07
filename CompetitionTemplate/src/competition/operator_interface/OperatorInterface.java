package competition.operator_interface;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import xbot.common.controls.sensors.JoystickButtonManager;
import xbot.common.controls.sensors.XJoystick;
import xbot.common.injection.wpi_factories.WPIFactory;
import xbot.common.logging.RobotAssertionManager;

/**
 * This class is the glue that binds the controls on the physical operator interface to the commands and command groups
 * that allow control of the robot.
 */
@Singleton
public class OperatorInterface {
    public XJoystick leftJoystick;
    public XJoystick rightJoystick;

    public JoystickButtonManager leftButtons;
    public JoystickButtonManager rightButtons;

    @Inject
    public OperatorInterface(WPIFactory factory, RobotAssertionManager assertionManager) {
        leftJoystick = factory.getJoystick(1);
        rightJoystick = factory.getJoystick(2);

        leftJoystick.setYInversion(true);
        rightJoystick.setXInversion(true);

        leftButtons = new JoystickButtonManager(8, factory, assertionManager, leftJoystick);
        rightButtons = new JoystickButtonManager(8, factory, assertionManager, rightJoystick);
    }
}
