package competition.subsystems.drive;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import xbot.common.controls.actuators.MockCANTalon;
import xbot.common.injection.BaseWPITest;

public class DriveSubsystemTest extends BaseWPITest {
    
    DriveSubsystem driveSubsystem;
    
    @Before
    public void init() {
        driveSubsystem = this.injector.getInstance(DriveSubsystem.class);
    }
    
    @Test
    public void testTankDrive() {
        driveSubsystem.tankDriveVelocityPid(1, 1);
        verifyDriveMotors(1, 1, TalonControlMode.Speed);
        
        driveSubsystem.tankDriveVelocityPid(1, 0);
        verifyDriveMotors(1, 0, TalonControlMode.Speed);
        
        driveSubsystem.tankDriveVelocityPid(-1, -.5);
        verifyDriveMotors(-1, 0-.5, TalonControlMode.Speed);
    }
    
    @Test
    public void testTooLargeInputToTankDrive() {
        driveSubsystem.tankDriveVelocityPid(2, 0);
        verifyDriveMotors(1, 0, TalonControlMode.Speed);
    }
    
    protected void verifyDriveMotors(double left, double right, TalonControlMode mode) {
        double leftTarget;
        double rightTarget;
        
        switch (mode) {
            case PercentVbus:
                leftTarget = left;
                rightTarget = right;
                break;
            case Speed:
                leftTarget = driveSubsystem.convertPowerToVelocityTarget(left);
                rightTarget = driveSubsystem.convertPowerToVelocityTarget(right);
                break;
        default:
            assertTrue("TalonControlMode " + mode.toString() + " is not supported by tests yet", false);
            leftTarget = 0;
            rightTarget = 0;
            break;
        }
        
        assertEquals(leftTarget, ((MockCANTalon)driveSubsystem.leftDrive).getSetpoint(), 0.001);
        assertEquals(rightTarget, ((MockCANTalon)driveSubsystem.rightDrive).getSetpoint(), 0.001);
        
        // also verify that the follower devices are configured properly
        verifyFollowerConfiguration();
    }
    
    protected void verifyFollowerConfiguration() {
        assertEquals(
                driveSubsystem.leftDrive.getDeviceID(), 
                ((MockCANTalon)driveSubsystem.leftDriveSlave).getSetpoint(), 
                0.001);
        assertEquals(
                driveSubsystem.rightDrive.getDeviceID(), 
                ((MockCANTalon)driveSubsystem.rightDriveSlave).getSetpoint(), 
                0.001);
    }
}
