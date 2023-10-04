// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 1;
    public static final int kDriverControllerPort2 = 0;
    public static final int kShotgunButton = 4;
    public static final int kSolenoidOneButtonID = 7;
    public static final int kSolenoidTwoButtonID = 8;
    public static final int kSolenoidThreeButtonID = 9;
    public static final int kSolenoidFourButtonID = 10;
    public static final int kSolenoidFiveButtonID = 11;
    public static final int kSolenoidSixButtonID = 12;
    public static final int kSolenoidSevenButtonID = 7;
    public static final int kSolenoidEightButtonID = 8;
    public static final int kSolenoidNineButtonID = 9;
    public static final int kSolenoidTenButtonID = 10;
    public static final int kSolenoidElevenButtonID = 11;
    public static final int kSolenoidTwelveButtonID = 12;
    public static final int kCompressorButtonID = 2;
    public static final int kArmButtonID = 3;
    public static final int kFireButtonID = 1;
  }
  public static class DriveConstants {
    public static final int kFrontRightMotorID = 0;
    public static final int kBackRightMotorID = 2;
    public static final int kFrontLeftMotorID = 1;
    public static final int kBackLeftMotorID = 3;
  }
  public static class PneumaticsConstants {
    public static final int kRevPneumaticPort = 2;
    public static final double kMinimumPressure = 90;
    public static final double kMaximumPressure = 100;

    // solenoid IDs
    public static final int kSolenoidOneID = 0;
    public static final int kSolenoidTwoID = 1;
    public static final int kSolenoidThreeID = 2;
    public static final int kSolenoidFourID = 3;
    public static final int kSolenoidFiveID = 4;
    public static final int kSolenoidSixID = 5;
    public static final int kSolenoidSevenID = 6;
    public static final int kSolenoidEightID = 7;
    public static final int kSolenoidNineID = 8;
    public static final int kSolenoidTenID = 9;
    public static final int kSolenoidElevenID = 10;
    public static final int kSolenoidTwelveID = 11;
  }
  public static class ArmConstants {
    public static final int kMotorOneID = 7;
  }
}
