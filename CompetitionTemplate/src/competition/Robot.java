
package competition;

import competition.operatorinterface.OperatorInterfaceCommandMap;
import competition.subsystems.SubsystemDefaultCommandMap;
import xbot.common.command.BaseRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends BaseRobot {

	@Override
	protected void initializeSystems() {
		super.initializeSystems();
		this.injector.getInstance(SubsystemDefaultCommandMap.class);
		this.injector.getInstance(OperatorInterfaceCommandMap.class);
    }
}
