// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.Constants.DriveConstants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.math.MathUtil;

public class DriveSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private TalonSRX frontRight = new TalonSRX(DriveConstants.kFrontRightMotorID);
  private TalonSRX backRight = new TalonSRX(DriveConstants.kBackRightMotorID);
  private TalonSRX frontLeft = new TalonSRX(DriveConstants.kFrontLeftMotorID);
  private TalonSRX backLeft = new TalonSRX(DriveConstants.kBackLeftMotorID);
  public DriveSubsystem() {}
 /**
   * Example command factory method.
   *
   * @return a command
   */
  public void drive(double speed, double rotation) {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    // return runOnce( -- i don't know why this cant be used but for some reason it doesn't work with this
        // () -> {
            // backLeft.setInverted(true);
            // frontLeft.setInverted(true);
        
            double xSpeed = MathUtil.applyDeadband(speed/**(-m_leftStick.getRawAxis(3)+1)*/, 0.1); // getrawaxis 3 is being wierd, i suggest switching to getY
            double zRotation = MathUtil.applyDeadband(rotation/**(-m_leftStick.getRawAxis(3)+1)*/, 0.1);
            // System.out.println(xSpeed);
        
            xSpeed = MathUtil.clamp(xSpeed, -1.0, 1.0);
            zRotation = MathUtil.clamp(zRotation, -1.0, 1.0);
        
            // Square the inputs (while preserving the sign) to increase fine control
            // while permitting full power.
            if (true) {
              xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
              zRotation = Math.copySign(zRotation * zRotation, zRotation);
            }
        
            double leftSpeed = xSpeed - zRotation;
            double rightSpeed = xSpeed + zRotation;
        
            // Find the maximum possible value of (throttle + turn) along the vector
            // that the joystick is pointing, then desaturate the wheel speeds
            double greaterInput = Math.max(Math.abs(xSpeed), Math.abs(zRotation));
            double lesserInput = Math.min(Math.abs(xSpeed), Math.abs(zRotation));
            double saturatedInput = (greaterInput + lesserInput) / greaterInput;
            leftSpeed /= saturatedInput;
            rightSpeed /= saturatedInput;
            
            // System.out.println(leftSpeed);
            backLeft.set(ControlMode.PercentOutput, leftSpeed);
            frontLeft.set(ControlMode.PercentOutput, leftSpeed);
            backRight.set(ControlMode.PercentOutput, rightSpeed);
            frontRight.set(ControlMode.PercentOutput, rightSpeed);
        };//);
  //}

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}

 