package org.firstinspires.ftc.teamcode.subsystems;

import java.util.concurrent.TimeUnit;

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

    public final double SERVO_OPEN = 0.181;
    public final double SERVO_CLOSED = 0.455;

    public Shooter(HardwareMap hwMap){
        shootingMotor=hwMap.get(DcMotorEx.class,"shootingMotor");
        shootingMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shootingMotor.setPower(0.0);
        shootingMotor.setVelocity(RobotVariables.SHOOTING_MOTOR_RPM*Constants.TICKS_PER_REVOLUTION/60.0); //TPS at 1.0 power

        shootingGate=hwMap.get(Servo.class, "shootingGate");
    }

    public void setPower(double power){
        shootingMotor.setPower(power);
    }
    public double getPower(){
        return shootingMotor.getPower();
    }

    public void setShooterVelocity(double ticksPerSecond){shootingMotor.setVelocity(-ticksPerSecond);}
    public double getShooterVelocity(){return -shootingMotor.getVelocity();}

    public void setServoDegree(double degree){
        shootingGate.setPosition(degree/180.0);
    }
    public double getServoDegree(){
        return shootingGate.getPosition()*180.0;
    }

    public double getServoPos(){return  shootingGate.getPosition();}
    public void setServoPos(double pos){shootingGate.setPosition(pos);}

    public void allowBalls(boolean allow){
        if (allow){shootingGate.setPosition(SERVO_OPEN);}
        else{shootingGate.setPosition(SERVO_CLOSED);}
    }

    public void runShootingSequence(int shootingVelocity) throws InterruptedException {
        allowBalls(true);
        setShooterVelocity(shootingVelocity);
        Thread.sleep((long) 2_000);
        allowBalls(false);
        setShooterVelocity(0);
    }
}
