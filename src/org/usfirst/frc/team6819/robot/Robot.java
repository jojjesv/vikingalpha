package org.usfirst.frc.team6819.robot;

import org.usfirst.frc.team6819.robot.subsystems.Drivebase;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
//import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
//import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
//import com.ctre.phoenix.motorcontrol.NeutralMode;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
//import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import org.usfirst.frc.team6819.robot.commands.ExampleCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot
{
	public static OI oi;
	public static Drivebase myDrivebase;
	
	public double L = -0.7;// -0.3	-1.2
	
	public boolean driveModeStill = false;
	
	private VictorSP armMotor;
	private VictorSP armPosMotor;
	
	@Override 
	public void robotInit()
	{
		oi = new OI();
		myDrivebase = new Drivebase();
		
		armMotor = new VictorSP(RobotMap.vindRuteMotorn);
		armPosMotor = new VictorSP(RobotMap.armPosMotor);
				
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	@Override
	public void disabledInit()
	{

	}
	
	@Override
	public void disabledPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
	@Override
	public void autonomousInit()
	{
		
	}
	
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
	public void TeleopInit()
	{
	
	}
	
	public void TeleopPeriodic()
	{
		Scheduler.getInstance().run();
	}
	
	@Override
	public void testInit()
	{
		SmartDashboard.putString("Test", "TestInitializes");
	}
	
	int i = 0;
	
	@SuppressWarnings("deprecation")
	@Override
	public void testPeriodic()
	{		
		LiveWindow.run();		
		SmartDashboard.putString("Test", "Test Run");
		
		double rightMotorValue = 0;
		double leftMotorValue = 0;
		double steerRight = 0;
		double steerLeft = 0;
		double throttle = (1.0 - oi.leftJoy.getThrottle() / -2.0);
		double deThrottle = 0.667;		
		
		
		//System.out.println(oi.getLeftJoyY());
		if(driveModeStill) 
		{
			//System.out.println("Y = 0");
			rightMotorValue = 1;
			leftMotorValue = 1;
			steerRight = oi.getRightJoyX() * 0.5;
			steerLeft = -1 * oi.getRightJoyX() * 0.5;
		}
		else
		{
//			double throttle = (1.0 - oi.leftJoy.getThrottle() / -2.0);
//			double deThrottle = 0.667;		
			rightMotorValue = oi.getLeftJoyY() * deThrottle * throttle;
			leftMotorValue  = oi.getLeftJoyY()  * deThrottle * throttle;		
		
			
			steerRight = -(1 - L) * oi.getRightJoyX() + 1;
			steerLeft = (1 - L) * oi.getRightJoyX() + 1;
			
			if (steerRight > 1)
				steerRight = 1;
			if (steerLeft > 1)
				steerLeft = 1;
			if (steerRight < -1)
				steerRight = -1;
			if (steerLeft < -1)
				steerLeft = -1;			
		}
			
		double roundLeft = (double)Math.round(steerLeft * leftMotorValue * deThrottle * throttle * 10) / 10;
		double roundRight = (double)Math.round(steerRight * rightMotorValue * deThrottle * throttle * 10) / 10;		
		if(roundLeft != 0)
			roundLeft *= -1;
		if(roundRight != 0)
			roundRight *= -1;		
		System.out.println(roundLeft + " " + roundRight);		
		
		myDrivebase.set(leftMotorValue * steerLeft * deThrottle * throttle, 
				rightMotorValue * steerRight * deThrottle * throttle);		
		//myDrivebase.Set(oi.getLeftJoyY() * 0.5, oi.getRightJoyY() * 0.5);
		
		
		
//		if(oi.buttonAPressed())
//		{
//			driveModeStill = !driveModeStill;
//			System.out.println(driveModeStill);
//		}
		
					
		//STYR ARMEN
		if (oi.buttonX())
		{
			armMotor.set(0.4);
			System.out.println("A");
		}		
		else if (oi.buttonA())
		{
			armMotor.set(-0.15);
			System.out.println("X");
		}		
		else 
		{
			armMotor.set(0);
		}
		
		
		//FLYTTA ARMEN
		if (oi.buttonB())
		{
			armPosMotor.set(0.3);
			System.out.println("B");
		}		
		else if (oi.buttonY())
		{
			armPosMotor.set(-0.2);
			System.out.println("Y");
		}		
		else 
		{
			armPosMotor.set(0);
		}
		
		
	}
	
	public static void initVictorSP(VictorSP motor)
	{
		//motor.setNeutralMode(NeutralMode.Coast);
		//motor.neutralOutput();
		//motor.setSensorPhase(false);
		//motor.configNominalOutputForward(0.0, 0);
		//motor.configNominalOutputReverse(0.0, 0);
		//motor.configClosedloopRamp(0.5, 0);
	}
}