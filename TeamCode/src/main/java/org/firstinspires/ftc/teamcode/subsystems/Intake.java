package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.teamcode.variables.Constants;
import org.firstinspires.ftc.teamcode.variables.RobotVariables;

public class Intake {
    CRServo leftIntakeServo;
   CRServo rightIntakeServo;

    DcMotorEx intakeMotor;

    public Intake(HardwareMap hwMap){
        leftIntakeServo=hwMap.get(CRServo.class,"LIS");
        rightIntakeServo=hwMap.get(CRServo.class,"RIS");

        intakeMotor=hwMap.get(DcMotorEx.class, "intake");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeMotor.setPower(0.0);
    }

    public double ticksToInches(double ticks){return ticks/(Constants.TICKS_PER_REVOLUTION / RobotVariables.WHEEL_CIRCUMFERENCE);}
    public double inchesToTicks(double inches){return inches*(Constants.TICKS_PER_REVOLUTION / RobotVariables.WHEEL_CIRCUMFERENCE);}

    public void setIntakePower (double power) {
        intakeMotor.setPower(power);
        power=(power+1.0)/2.0;
        setServosPower(power, power);
    }
    public double getIntakePower() {return intakeMotor.getPower();}

    public void setServosPower(double powerL, double powerR){
        leftIntakeServo.setPower(1-powerL);
        rightIntakeServo.setPower(powerR);
    }

}
