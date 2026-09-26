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

    //Motors
    DcMotorEx fl;;
    DcMotorEx fr;
    DcMotorEx bl;
    DcMotorEx br;

    public DriveTrain(HardwareMap hwMap){
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

        IMU imu = hwMap.get(IMU.class, "imu");
        // Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        // Without this, the REV Hub's orientation is assumed to be logo up / USB forward
        imu.initialize(parameters);
    }

    public double getSpeed(){
        return speed;
    }
    public void setSpeed(double speed){
        this.speed=speed;
    }

    public void setPower(double rf, double rb, double lf, double lb){
        fr.setPower(rf);
        br.setPower(rb);
        bl.setPower(lb);
        fl.setPower(lf);
    }
    public void setPowerAll(double power){
        fr.setPower(power);
        br.setPower(power);
        bl.setPower(power);
        fl.setPower(power);
    }



    public void move(Gamepad gamepad1){
        double y = -gamepad1.left_stick_y; // Remember, Y stick value is reversed
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        // This button choice was made so that it is hard to hit on accident,
        // it can be freely changed based on preference.
        // The equivalent button is start on Xbox-style controllers.
        if (gamepad1.options) {
            imu.resetYaw();
        }

        double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
        double frontLeftPower = (rotY + rotX + rx) / denominator;
        double backLeftPower = (rotY - rotX + rx) / denominator;
        double frontRightPower = (rotY - rotX - rx) / denominator;
        double backRightPower = (rotY + rotX - rx) / denominator;

        setPower(frontRightPower, backRightPower, frontLeftPower, backLeftPower);
    }


}
