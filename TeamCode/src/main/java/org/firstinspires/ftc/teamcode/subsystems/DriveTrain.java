package org.firstinspires.ftc.teamcode.subsystems;

//Class contains all drivetrain motors and methods

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

//Rotational Position
import com.qualcomm.robotcore.hardware.IMU;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.variables.Constants;

public class DriveTrain {
    //Technical Stuff
    IMU imu;

    //Vars
    double speed;
    public boolean parked;

    //Motors
    public DcMotorEx lf;
    public DcMotorEx rf;
    public DcMotorEx lb;
    public DcMotorEx rb;

    public DriveTrain(HardwareMap hwMap, IMU imu){
        lf=hwMap.get(DcMotorEx.class,"fl");
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setPower(0.0);

        rf =hwMap.get(DcMotorEx.class,"fr");
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lb =hwMap.get(DcMotorEx.class,"bl");
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setPower(0.0);

        rb =hwMap.get(DcMotorEx.class,"br");
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setPower(0.0);

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

    public void setPower(double fr, double br, double fl, double bl){
        if(parked){return;}

        this.rf.setPower(fr);
        this.rb.setPower(br);
        this.lb.setPower(bl);
        lf.setPower(fl);
    }
    public void setPowerAll(double power){
        if(parked){return;}

        rf.setPower(power);
        rb.setPower(power);
        lb.setPower(power);
        lf.setPower(power);
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

    // Goes straight
    public void moveVector(double inches, double movementAngle, double power, double turnAngle){
        rf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lf.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lb.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        if(turnAngle>180){turnAngle=turnAngle-360;}
        double straight=inches*Math.sin(movementAngle);
        double strafe=inches*Math.cos(movementAngle);
        double turnDistance=turnAngle/360.0*2*Math.PI*Constants.WHEEL_DISTANCE_FROM_CENTER;

        lf.setTargetPosition((int)(inchesToTicks(straight+strafe+turnDistance)));
        lb.setTargetPosition((int)(inchesToTicks(straight-strafe+turnDistance)));
        rf.setTargetPosition((int)(inchesToTicks(straight-strafe-turnDistance)));
        rb.setTargetPosition((int)(inchesToTicks(straight+strafe-turnDistance)));

        double max=Math.max(Math.max(straight+strafe+turnDistance,straight-strafe+turnDistance),Math.max(straight-strafe-turnDistance, straight+strafe-turnDistance));

        rf.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lf.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        rb.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        lb.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);

        setPower(
                power*(straight-strafe-turnDistance)/max,
                power*(straight+strafe-turnDistance)/max,
                power*(straight+strafe+turnDistance)/max,
                power*(straight-strafe+turnDistance)/max);

    }

    public double ticksToInches(double ticks){
        return ticks/(Constants.TICKS_PER_REVOLUTION/Constants.WHEEL_CIRCUMFERENCE);
    }

    public double inchesToTicks(double inches){
        return inches*(Constants.TICKS_PER_REVOLUTION/Constants.WHEEL_CIRCUMFERENCE);
    }


}
