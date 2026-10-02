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
    Servo shootingGate; //Assumed to be 180 degree servo

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
        shootingGate.setPosition(degree/180.0);
    }
    public double getServoDegree(){
        return shootingGate.getPosition()*180.0;
    }

    public void allowBalls(boolean allow){
        if (allow){shootingGate.setPosition(0.0);}
            else{shootingGate.setPosition(0.5);}
    }
}
