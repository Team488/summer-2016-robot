
package competition;

import competition.operator_interface.OperatorInterfaceCommandMap;
import competition.subsystems.SubsystemDefaultCommandMap;
import xbot.common.wpi_extensions.BaseRobot;

public class Robot extends BaseRobot {

	@Override
	protected void initializeSystems() {
		super.initializeSystems();
		this.injector.getInstance(SubsystemDefaultCommandMap.class);
		this.injector.getInstance(OperatorInterfaceCommandMap.class);
    }
}
