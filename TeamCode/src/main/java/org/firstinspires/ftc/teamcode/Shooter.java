package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Shooter {
    DcMotorEx shootingMotor;

    public Shooter(HardwareMap hwMap){
        shootingMotor=hwMap.get(DcMotorEx.class,"shootingMotor");
        shootingMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        shootingMotor.setPower(0.0);
    }

    public void setPower(double power){
        shootingMotor.setPower(power);
    }

    public double getPower(){
        return shootingMotor.getPower();
    }
}
