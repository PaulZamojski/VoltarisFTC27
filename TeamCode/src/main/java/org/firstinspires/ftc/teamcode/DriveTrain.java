package org.firstinspires.ftc.teamcode;

//Class contains all drivetrain motors and methods

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {
    double speed;
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
        double forward;
        double sideways;
        double turning;
        double max;
        double scaleFactor;

        forward = -(Math.atan(5 * gamepad1.left_stick_y) / Math.atan(5));
        sideways = (Math.atan(5 * gamepad1.left_stick_x) / Math.atan(5));
        turning = (Math.atan(5 * gamepad1.right_stick_x) / Math.atan(5));

        max = Math.max(Math.abs(forward - sideways - turning), Math.max(Math.abs(forward + sideways - turning), Math.max(Math.abs(forward + sideways + turning), Math.abs(forward + turning - sideways))));
        if (max > speed) {
            scaleFactor = speed/max;
        } else {
            scaleFactor = speed;
        }
        scaleFactor = Math.max(Math.abs(1), 0.2);

        setPower(
                (forward - sideways - turning) * scaleFactor,
                (forward + sideways - turning) * scaleFactor,
                (forward + sideways + turning) * scaleFactor,
                (forward + turning - sideways) * scaleFactor);
    }


}
