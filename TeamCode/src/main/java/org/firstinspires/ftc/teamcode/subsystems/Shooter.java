package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.variables.Constants;
import org.firstinspires.ftc.teamcode.variables.RobotVariables;

import javax.lang.model.element.VariableElement;

public class Shooter {
    DcMotorEx shootingMotor;
    Servo shootingGate;

    public Shooter(HardwareMap hwMap){
        shootingMotor=hwMap.get(DcMotorEx.class,"shootingMotor");
        shootingMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shootingMotor.setPower(0.0);
        shootingMotor.setVelocity(RobotVariables.SHOOTING_MOTOR_RPM*Constants.TICKS_PER_REVOLUTION/60.0);

        shootingGate=hwMap.get(Servo.class, "shootingGate");
    }

    public void setPower(double power){
        shootingMotor.setPower(power);
    }
    public double getPower(){
        return shootingMotor.getPower();
    }

    public void setServoDegree(double degree){
        shootingGate.setPosition(degree/1800.0);
    }
    public double getServoDegree(){
        return shootingGate.getPosition()*1800.0;
    }
}
