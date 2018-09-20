package org.usfirst.frc.team6819.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI
{
	public static final double joyDeadzone = 0.01;
	
	public final Joystick leftJoy;
	public final JoystickButton buttonA;
	
	public OI()
	{
		leftJoy = new Joystick(RobotMap.leftJoy);
		
		buttonA = new JoystickButton(leftJoy, 1);
	}
	
	public boolean buttonA()
	{
		return leftJoy.getRawButton(RobotMap.buttonA);
	}

	public boolean buttonB()
	{
		return leftJoy.getRawButton(RobotMap.buttonB);
	}
	
	public boolean buttonX()
	{
		return leftJoy.getRawButton(RobotMap.buttonX);
	}

	public boolean buttonY()
	{
		return leftJoy.getRawButton(RobotMap.buttonY);
	}
	
	public double getLeftJoyX()
	{
		double raw = leftJoy.getX();
		
		if(Math.abs(raw) < joyDeadzone)
			return 0.0;
		else
			return raw;
		//return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;		
	}
	
	public double getLeftJoyY()
	{
		double raw = leftJoy.getY();
		
		if(Math.abs(raw) < joyDeadzone)
			return 0.0;
		else
			return raw;
		//return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;		
	}
	
	public double getRightJoyX()
	{
		double raw = leftJoy.getRawAxis(4);
		
		if(Math.abs(raw) < joyDeadzone)
			return 0.0;
		else
			return raw;
		//return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;		
	}
	
	public double getRightJoyY()
	{
		//double raw = leftJoy.getY(Hand.kRight);
		double raw = leftJoy.getRawAxis(5);
		
		if(Math.abs(raw) < joyDeadzone)
			return 0.0;
		else
			return raw;
		//return Math.abs(raw) < JOY_DEADZONE ? 0.0 : raw;		
	}
}