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
    Servo rightIntakeServo;
    DcMotorEx intakeMotor;

    public final double WIPER_OPEN = 0.362;
    public final double WIPER_CLOSED = 0.950;

    public Intake(HardwareMap hwMap){
        leftIntakeServo=hwMap.get(CRServo.class,"LIS");
        rightIntakeServo=hwMap.get(Servo.class,"RIS");

        intakeMotor=hwMap.get(DcMotorEx.class, "intake");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeMotor.setPower(0.0);
    }

    public double ticksToInches(double ticks){return ticks/(Constants.TICKS_PER_REVOLUTION / RobotVariables.WHEEL_CIRCUMFERENCE);}
    public double inchesToTicks(double inches){return inches*(Constants.TICKS_PER_REVOLUTION / RobotVariables.WHEEL_CIRCUMFERENCE);}

    public void setIntakePower (double power) {
        intakeMotor.setPower(1*power);
        setServosPower(-power);
    }
    public double getIntakePower() {return intakeMotor.getPower();}

    public void setServosPower(double powerL){
        leftIntakeServo.setPower(powerL);
    }

    public void setWiperPos(double pos){
        rightIntakeServo.setPosition(pos);
    }

}
