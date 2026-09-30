package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

public class Intake {
    Servo leftIntakeServo;
    Servo rightIntakeServo;

    DcMotorEx intakeMotor;

    public Intake(HardwareMap hwMap){
        leftIntakeServo=hwMap.get(Servo.class,"leftIntakeServo");
        rightIntakeServo=hwMap.get(Servo.class,"rightIntakeServo");

        intakeMotor=hwMap.get(DcMotorEx.class, "intakeMotor");
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        intakeMotor.setPower(0.0);
    }
}
