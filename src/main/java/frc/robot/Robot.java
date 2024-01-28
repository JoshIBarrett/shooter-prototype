// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

   private TalonFX testMotor = new TalonFX(0, "rio");

   private TunableNumber testMotorTuner;

  @Override
  public void robotInit() {
    testMotor.config_kP(0, 0.3);
    testMotor.config_kI(0, 0.0);
    testMotor.config_kD(0, 0.03);
    testMotor.config_kF(0, 0.0);
    testMotor.setNeutralMode(NeutralMode.Coast);
    testMotor.configClosedloopRamp(0.1);
    testMotor.setInverted(false);
    
    
    testMotorTuner = new TunableNumber("Top Left Falcon RPS", 0.0, true);
    testMotor.set(ControlMode.Velocity, 0.0);
  }

  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    if(testMotorTuner.get() > 0.0) {
      testMotor.set(ControlMode.Velocity, testMotorTuner.get() / (10.0 / 2048.0));
    } else {
      testMotor.set(ControlMode.Velocity, 0.0);
    }

  

    // getSelectedSensorVelocity is in ticks/100ms, need to multiply by rot/2048 ticks to get rot/100ms, then 1000ms/1s to get rot/s
    SmartDashboard.putNumber("Top Left ACTUAL Speed RPS", Math.abs(testMotor.getSelectedSensorVelocity() * (10.0 / 2048.0)));


    SmartDashboard.putNumber("Top Left Percent Output", 100 * testMotor.getMotorOutputVoltage() / RobotController.getBatteryVoltage());
   
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
