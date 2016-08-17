package competition.subsystems.drive;

import static org.junit.Assert.*;

import org.junit.Test;

import xbot.common.injection.BaseWPITest;

public class DriveSubsystemTest extends BaseWPITest {
    @Test
    public void testTankDrive() {
        DriveSubsystem driveSubsystem = this.injector.getInstance(DriveSubsystem.class);
        driveSubsystem.tankDrive(1, 1);

        // TODO: Figure out the testing and implement
        
        //assertEquals(this.mockRobotIO.getCANTalon(driveSubsystem.leftDrive.getDeviceID()).getOutputVoltage(), 1, 0.001);
        //assertEquals(this.mockRobotIO.getPWM(driveSubsystem.leftRearDrive.getChannel()), 1, 0.001);
        //assertEquals(this.mockRobotIO.getPWM(driveSubsystem.rightFrontDrive.getChannel()), 1, 0.001);
        //assertEquals(this.mockRobotIO.getPWM(driveSubsystem.rightRearDrive.getChannel()), 1, 0.001);
    }
}
