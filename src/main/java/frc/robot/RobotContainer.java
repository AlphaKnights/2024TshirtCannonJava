// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.PneumaticsSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

import org.ejml.dense.block.MatrixMult_FDRB;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.FireSpecific;
import frc.robot.commands.Shotgun;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import java.util.function.BooleanSupplier;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final PneumaticsSubsystem m_PneumaticsSubsystem = new PneumaticsSubsystem();
  private final DriveSubsystem m_robotDrive = new DriveSubsystem();
  private final ArmSubsystem m_sally = new ArmSubsystem();
  private final Joystick m_Joystick = new Joystick(OperatorConstants.kDriverControllerPort);
  private final Joystick m_Joystick2 = new Joystick(OperatorConstants.kDriverControllerPort2);
  private final JoystickButton m_ShotgunButton = new JoystickButton(m_Joystick, OperatorConstants.kShotgunButton);
  private final JoystickButton m_ArmButton = new JoystickButton(m_Joystick2, OperatorConstants.kArmButtonID);
  private final JoystickButton m_CompressorButton = new JoystickButton(m_Joystick, OperatorConstants.kShotgunButton);
  private final JoystickButton m_semiAutoButton = new JoystickButton(m_Joystick, OperatorConstants.kFireButtonID);
  private final JoystickButton m_fireButton = new JoystickButton(m_Joystick2, OperatorConstants.kFireButtonID);
  private final JoystickButton[] m_FireSingleButtons = {
    new JoystickButton(m_Joystick, OperatorConstants.kSolenoidOneButtonID),
    new JoystickButton(m_Joystick, OperatorConstants.kSolenoidTwoButtonID),
    new JoystickButton(m_Joystick, OperatorConstants.kSolenoidThreeButtonID),
    new JoystickButton(m_Joystick, OperatorConstants.kSolenoidFourButtonID),
    new JoystickButton(m_Joystick, OperatorConstants.kSolenoidFiveButtonID),
    new JoystickButton(m_Joystick, OperatorConstants.kSolenoidSixButtonID),
    new JoystickButton(m_Joystick2, OperatorConstants.kSolenoidSevenButtonID),
    new JoystickButton(m_Joystick2, OperatorConstants.kSolenoidEightButtonID),
    new JoystickButton(m_Joystick2, OperatorConstants.kSolenoidNineButtonID),
    new JoystickButton(m_Joystick2, OperatorConstants.kSolenoidTenButtonID),
    new JoystickButton(m_Joystick2, OperatorConstants.kSolenoidElevenButtonID),
    new JoystickButton(m_Joystick2, OperatorConstants.kSolenoidTwelveButtonID)
  };
  private final FireSpecific[] m_FireSpecific = {
    new FireSpecific(m_PneumaticsSubsystem, 0),
    new FireSpecific(m_PneumaticsSubsystem, 1),
    new FireSpecific(m_PneumaticsSubsystem, 2),
    new FireSpecific(m_PneumaticsSubsystem, 3),
    new FireSpecific(m_PneumaticsSubsystem, 4),
    new FireSpecific(m_PneumaticsSubsystem, 5),
    new FireSpecific(m_PneumaticsSubsystem, 6),
    new FireSpecific(m_PneumaticsSubsystem, 7),
    new FireSpecific(m_PneumaticsSubsystem, 8),
    new FireSpecific(m_PneumaticsSubsystem, 9),
    new FireSpecific(m_PneumaticsSubsystem, 10),
    new FireSpecific(m_PneumaticsSubsystem, 11)
  };

  BooleanSupplier stringEquals = () -> equalBoolArray(m_PneumaticsSubsystem.getToFire(), new boolean[12]);

  private final BooleanSupplier resetStringEquals(){
    BooleanSupplier stringEquals = () -> equalBoolArray(m_PneumaticsSubsystem.getToFire(), new boolean[12]);
    return stringEquals;
  }

  private final Shotgun fireShotgun = new Shotgun(m_PneumaticsSubsystem);
  // private final FireSpecific m_FireFirst = new FireSpecific(m_PneumaticsSubsystem, 0);
  // private final FireSpecific m_FireSecond = new FireSpecific(m_PneumaticsSubsystem, 1);
  // private final FireSpecific m_FireThird = new FireSpecific(m_PneumaticsSubsystem, 2);
  // private final FireSpecific m_FireFourth = new FireSpecific(m_PneumaticsSubsystem, 3);
  // private final FireSpecific m_FireFifth = new FireSpecific(m_PneumaticsSubsystem, 4);
  // private final FireSpecific m_FireSixth = new FireSpecific(m_PneumaticsSubsystem, 5);
  // private final FireSpecific m_FireSeventh = new FireSpecific(m_PneumaticsSubsystem, 6);
  // private final FireSpecific m_FireEighth = new FireSpecific(m_PneumaticsSubsystem, 7);
  // private final FireSpecific m_FireNinth = new FireSpecific(m_PneumaticsSubsystem, 8);
  // private final FireSpecific m_FireTenth = new FireSpecific(m_PneumaticsSubsystem, 9);
  // private final FireSpecific m_FireEleventh = new FireSpecific(m_PneumaticsSubsystem, 10);
  // private final FireSpecific m_FireTwelvth = new FireSpecific(m_PneumaticsSubsystem, 11);

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandJoystick m_driverController =
      new CommandJoystick(OperatorConstants.kDriverControllerPort);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    m_FireSingleButtons[0].whileTrue(m_FireSpecific[0]);
    //m_FireSingleButtons[0].onTrue(m_FireSpecific[0]);
    m_FireSingleButtons[1].whileTrue(m_FireSpecific[1]);
    m_FireSingleButtons[2].whileTrue(m_FireSpecific[2]);
    m_FireSingleButtons[3].whileTrue(m_FireSpecific[3]);
    m_FireSingleButtons[4].whileTrue(m_FireSpecific[4]);
    m_FireSingleButtons[5].whileTrue(m_FireSpecific[5]);
    m_FireSingleButtons[6].whileTrue(m_FireSpecific[6]);
    m_FireSingleButtons[7].whileTrue(m_FireSpecific[7]);
    m_FireSingleButtons[8].whileTrue(m_FireSpecific[8]);
    m_FireSingleButtons[9].whileTrue(m_FireSpecific[9]);
    m_FireSingleButtons[10].whileTrue(m_FireSpecific[10]);
    //m_FireSingleButtons[11].onTrue(m_FireSpecific[11]);
    // for (int i = 0; i<m_FireSingleButtons.length;i++){
    //   m_FireSingleButtons[i].onTrue(m_FireSpecific[i]);
    // }

    m_ShotgunButton.onTrue(fireShotgun);

    //m_CompressorButton.onTrue(new RunCommand(() -> m_PneumaticsSubsystem.toggleCompressor(), m_PneumaticsSubsystem));

    // m_ArmButton.onTrue( new RunCommand(() -> m_sally.bringSallyUp(m_Joystick.getThrottle()), m_sally));
    m_sally.setDefaultCommand(new RunCommand(() -> m_sally.bringSallyUp(m_Joystick2.getY()), m_sally));

    m_fireButton.onTrue((new InstantCommand(() -> m_PneumaticsSubsystem.fireToFire(), m_PneumaticsSubsystem)).andThen(new WaitCommand(1)).andThen(new InstantCommand(() -> m_PneumaticsSubsystem.fireToFire(), m_PneumaticsSubsystem)));

    m_semiAutoButton.onTrue((new InstantCommand(() -> m_PneumaticsSubsystem.burstFire(), m_PneumaticsSubsystem).andThen(new WaitCommand(0.25))).repeatedly().until(resetStringEquals()).andThen(new WaitCommand(1).andThen((new InstantCommand(() -> m_PneumaticsSubsystem.fireToFire(), m_PneumaticsSubsystem)))));

    // m_sally.setDefaultCommand( new RunCommand(() -> m_sally.bringSallyUp(m_Joystick.getThrottle()), m_sally));
    
    m_robotDrive.setDefaultCommand(
        // The left stick controls translation of the robot.
        // Turning is controlled by the X axis of the right stick.
        new RunCommand(() -> m_robotDrive.drive(m_Joystick.getX(), m_Joystick.getY()), m_robotDrive));
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
  public boolean equalBoolArray(boolean[] x, boolean[] y){ // probably put this in a util file later
    if (x.length == y.length){
      for(int i = 0; i<x.length; i++){
        if (x[i]!=y[i]){
          return false;
        }
      }
      return true;
    }
    else{
      return false;
    }
  }
}
