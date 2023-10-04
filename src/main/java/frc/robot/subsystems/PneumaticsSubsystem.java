// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import frc.robot.Constants.PneumaticsConstants;
import edu.wpi.first.wpilibj.PneumaticHub;

public class PneumaticsSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  PneumaticHub pneumaticHub = new PneumaticHub(PneumaticsConstants.kRevPneumaticPort);
  // Solenoid m_solenoid = pneumaticHub.makeSolenoid(0);
  private Compressor compressor = new Compressor(PneumaticsModuleType.REVPH);
  Solenoid[] solenoids = {
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidOneID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidTwoID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidThreeID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidFourID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidFiveID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidSixID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidSevenID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidEightID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidNineID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidTenID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidElevenID),
    pneumaticHub.makeSolenoid(PneumaticsConstants.kSolenoidTwelveID),
    };
  boolean[] toFire = new boolean[solenoids.length];
  public PneumaticsSubsystem() {}

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
  public void toggleCompressor(){
    if (compressor.isEnabled()){
        compressor.disable();
    }
    else{
        compressor.enableAnalog(PneumaticsConstants.kMinimumPressure, PneumaticsConstants.kMaximumPressure);
    }
  }
  public void activateSolenoids(int[] ids){
    for(int i : ids){
//      solenoids[i].set(true);
      toFire[i] = true;
      System.out.println("hi");
    }
  }
  public void disableSolenoids(int[] ids){
    for(int i : ids){
//        solenoids[i].set(false);
      toFire[i] = false;
        System.out.println("bye");
    }
  }
  public void toggleSolenoids(int[] ids){
    for(int i : ids){
//        solenoids[i].toggle();;
      toFire[i] = !toFire[i];
      System.out.println("lksdj");
    }
  }
  public void fireToFire(){
    for(int i = 0; i<solenoids.length; i++){
      // if (toFire[i]){
      // // fire(solenoids[i]);
      // solenoids[i].toggle();
      // }
      solenoids[i].set(toFire[i]);
      toFire[i]=false;
    //     solenoids[i].set(toFire[i]);
    //     System.out.println(toFire[i]);
    //     toFire[i] = false;
    // }
    // new WaitCommand(0.5);
    // for(int i = 0; i<solenoids.length; i++){
    //   solenoids[i].set(false);
    }
  }
  public void burstFire(){
    for(int i = 0; i<solenoids.length; i++){
      if (toFire[i]){
        solenoids[i].set(true);
        toFire[i] = false;
        break;
      }
    }
    System.out.println("uh yeah i guess");
  }
  public boolean[] getToFire(){
    return toFire;
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
