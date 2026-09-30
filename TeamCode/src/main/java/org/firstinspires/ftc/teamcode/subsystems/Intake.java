package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Intake {
    Servo leftIntakeServo;
    Servo rightIntakeServo;


    public Intake(HardwareMap hwMap){
        leftIntakeServo=hwMap.get(Servo.class,"leftIntakeServo");
        rightIntakeServo=hwMap.get(Servo.class,"rightIntakeServo");
    }
}
