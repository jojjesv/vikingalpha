package org.usfirst.frc.team6819.robot.commands;

import org.usfirst.frc.team6819.robot.Robot;

//import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class TankDrive extends Command
{
	public TankDrive()
	{
		requires(Robot.myDrivebase);
		SmartDashboard.putString("TankDrive", "EXISTS!");
	}
	
	protected void Initialize()
	{
		
	}

	protected void Execute()
	{
		//double throttle ar viktigt att komma ihag till framtiden
		double throttle = (1.0 - Robot.oi.leftJoy.getThrottle()) / -2.0;
		
		Robot.myDrivebase.set(Robot.oi.getLeftJoyY() * throttle, 
				Robot.oi.getRightJoyY() * throttle);//ControlMode.PercentOutput, 
	}
	
	protected boolean isFinished()
	{
		return false;
	}
	
	protected void Interrupted()
	{
		end();
	}
}