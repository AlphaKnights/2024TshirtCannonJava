// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenixpro.hardware.TalonFX;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.math.MathUtil;

public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private TalonFX m_ArmMotor = new TalonFX(ArmConstants.kMotorOneID);
  public ArmSubsystem() {
    m_ArmMotor.setInverted(false);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public CommandBase exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }
  public void bringSallyUp(double speed) {
    m_ArmMotor.set(MathUtil.applyDeadband(speed/4/**(-m_leftStick.getRawAxis(3)+1)*/, 0.1));
  }
  public void setSallysAngle(double radians) {
    //to be implemented if neccessary
  }
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
