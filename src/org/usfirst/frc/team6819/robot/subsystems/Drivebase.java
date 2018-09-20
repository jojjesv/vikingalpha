package org.usfirst.frc.team6819.robot.subsystems;

import org.usfirst.frc.team6819.robot.Robot;
import org.usfirst.frc.team6819.robot.RobotMap;
import org.usfirst.frc.team6819.robot.commands.TankDrive;

//import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.VictorSP;

//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Drivebase extends Subsystem
{
	private VictorSP LeftMotor;
	private VictorSP LeftMotorFollower;
	private VictorSP RightMotor;
	private VictorSP RightMotorFollower;
	
	@SuppressWarnings("deprecation")
	public Drivebase()
	{
		LeftMotor 			= new VictorSP(RobotMap.leftMotor);
		RightMotor 			= new VictorSP(RobotMap.rightMotor);
		LeftMotorFollower 	= new VictorSP(RobotMap.leftMotorFollower);
		RightMotorFollower 	= new VictorSP(RobotMap.rightMotorFollower);
		
		Robot.initVictorSP(LeftMotor);
		Robot.initVictorSP(RightMotor);
		Robot.initVictorSP(LeftMotorFollower);
		Robot.initVictorSP(RightMotorFollower);

		LiveWindow.addActuator("Drive", "Left Side", LeftMotor);
		LiveWindow.addActuator("Drive", "Left Side", LeftMotorFollower);
		LiveWindow.addActuator("Drive", "Right Side", RightMotor);
		LiveWindow.addActuator("Drive", "Right Side", RightMotorFollower);
	}
	
	public void set(double leftvalue, double rightvalue)//ControlMode mode,
	{
		LeftMotor.set(leftvalue * -1);
		LeftMotorFollower.set(leftvalue * -1);
		RightMotor.set(rightvalue);
		RightMotorFollower.set(rightvalue);
	}
	
	protected void initDefaultCommand()
	{
		setDefaultCommand(new TankDrive());
	}
}