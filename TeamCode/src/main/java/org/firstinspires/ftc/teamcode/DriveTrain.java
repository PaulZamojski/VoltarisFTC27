package org.firstinspires.ftc.teamcode;

//Class contains all drivetrain motors and methods

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

//Rotational Position
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class DriveTrain {
    //Technical Stuff
    IMU imu;

    //Vars
    double speed;
    boolean parked;

    //Motors
    DcMotorEx fl;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;

    public DriveTrain(HardwareMap hwMap, IMU imu){
        fl=hwMap.get(DcMotorEx.class,"fl");
        fl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        fl.setPower(0.0);

        fr=hwMap.get(DcMotorEx.class,"fr");
        fr.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        bl=hwMap.get(DcMotorEx.class,"bl");
        bl.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        bl.setPower(0.0);

        br=hwMap.get(DcMotorEx.class,"br");
        br.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        br.setPower(0.0);

        speed=0;
        parked=true;

        this.imu=imu;
    }

    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double speed){
        if(parked){return;}

        this.speed=speed;
    }

    public void setPower(double rf, double rb, double lf, double lb){
        if(parked){return;}

        fr.setPower(rf);
        br.setPower(rb);
        bl.setPower(lb);
        fl.setPower(lf);
    }
    public void setPowerAll(double power){
        if(parked){return;}

        fr.setPower(power);
        br.setPower(power);
        bl.setPower(power);
        fl.setPower(power);
    }



    public void move(Gamepad gamepad1){
        if(parked){return;}

        double y = -gamepad1.left_stick_y; //Y stick value is reversed
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;


        if (gamepad1.start) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        setPower(frontRightPower, backRightPower, frontLeftPower, backLeftPower);
    }


}
