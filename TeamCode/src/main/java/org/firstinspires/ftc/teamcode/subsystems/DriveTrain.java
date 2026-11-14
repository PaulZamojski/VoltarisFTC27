package org.firstinspires.ftc.teamcode.subsystems;

//Class contains all drivetrain motors and methods

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;


import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.UnnormalizedAngleUnit;
import org.firstinspires.ftc.teamcode.pedro.procedures.PinpointTuner;
import org.firstinspires.ftc.teamcode.variables.Constants;
import org.firstinspires.ftc.teamcode.variables.RobotVariables;

import java.security.PublicKey;

public class DriveTrain {
    //Technical Stuff
    GoBildaPinpointDriver pinpoint;

    //Vars
    public boolean parked;

    //Motors
    public DcMotorEx lf;
    public DcMotorEx rf;
    public DcMotorEx lb;
    public DcMotorEx rb;

    public final double PI = Math.PI;

    
    public DriveTrain(HardwareMap hwMap, GoBildaPinpointDriver pinpoint){
        lf = hwMap.get(DcMotorEx.class,"fl");
        lf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lf.setPower(0.0);

        rf = hwMap.get(DcMotorEx.class,"fr");
        rf.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        lb = hwMap.get(DcMotorEx.class,"bl");
        lb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lb.setPower(0.0);

        rb = hwMap.get(DcMotorEx.class,"br");
        rb.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rb.setPower(0.0);

        parked = true;

        this.pinpoint = pinpoint;
    }


    public void setPower(double fr, double br, double fl, double bl){
        if(parked)return;

        this.rf.setPower(fr);
        this.rb.setPower(br);
        this.lb.setPower(-bl);
        this.lf.setPower(-fl);
    }
    public void setPowerAll(double power){
        if(parked){return;}

        rf.setPower(power);
        rb.setPower(power);
        lb.setPower(power);
        lf.setPower(power);
    }


    public static double sin(double degrees) {return Math.sin(degrees);}
    public static double cos(double degrees) {return Math.cos(degrees);}

    public double ticksToInches(double ticks){return ticks/(Constants.TICKS_PER_REVOLUTION / RobotVariables.WHEEL_CIRCUMFERENCE);}
    public double inchesToTicks(double inches){return inches*(Constants.TICKS_PER_REVOLUTION / RobotVariables.WHEEL_CIRCUMFERENCE);}


    public double getDirection(){
        pinpoint.update();
        return pinpoint.getHeading(UnnormalizedAngleUnit.DEGREES);
    }
    public void move(Gamepad gamepad1, boolean fieldOriented, float trigger){
        double multiplier = (0.28125 / (trigger + .25)) - .125;
        if (multiplier > 1){multiplier = 1;}

        if(parked){return;}

        double y = -gamepad1.left_stick_y; //Y stick value is reversed
        double x = gamepad1.left_stick_x;
        double rx = gamepad1.right_stick_x;

        if (gamepad1.start) {
            pinpoint.resetPosAndIMU();
        }

        pinpoint.update();
        double botHeading =pinpoint.getHeading(AngleUnit.RADIANS);

        // Rotate the movement direction counter to the bot's rotation
        if(fieldOriented) {
            double x0 = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            y = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);
            x=x0;
        }

        x = x * 1.1;  // Counteract imperfect strafing

        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
        double frontLeftPower = multiplier * (y + x + rx) / denominator;
        double backLeftPower = multiplier * (y - x + rx) / denominator;
        double frontRightPower = multiplier * (y - x -  rx) / denominator;
        double backRightPower = multiplier * (y + x - rx) / denominator;


        setPower(frontRightPower, backRightPower, frontLeftPower, backLeftPower);
    }

    // Goes straight
    public void moveVector(double inches, double movementAngle, double power, double turnAngle, boolean xDirection, boolean yDirection){

        if(turnAngle>180){turnAngle=turnAngle-360;}
        double straight=inches*Math.sin(movementAngle);
        double strafe=inches*Math.cos(movementAngle);
        double turnDistance=turnAngle/360.0*2*PI*RobotVariables.WHEEL_DISTANCE_FROM_CENTER;


        double max=Math.max(Math.max(straight + strafe + turnDistance, straight - strafe + turnDistance), Math.max(straight - strafe - turnDistance, straight + strafe - turnDistance));

        pinpoint.update();
        double x = pinpoint.getEncoderX();
        double y = pinpoint.getEncoderY();


        System.out.println("Movement angle: "+movementAngle+"X: "+xDirection+"Y: "+yDirection);
        setPower(
                power * (straight-strafe-turnDistance) / max,
                power * (straight+strafe-turnDistance) / max,
                power * (straight+strafe+turnDistance) / max,
                power * (straight-strafe+turnDistance) / max);
        System.out.println("Set powers: "+power*(straight-strafe-turnDistance)/max);


        if (yDirection){
            if (xDirection){
                while (pinpoint.getEncoderX()<x || pinpoint.getEncoderY()<y){pinpoint.update();System.out.println("going");}
            } else {
                while (pinpoint.getEncoderX()>x || pinpoint.getEncoderY()<y){pinpoint.update();System.out.println("going");}
            }
        } else {
            if (xDirection){
                while (pinpoint.getEncoderX()<x || pinpoint.getEncoderY()>y){pinpoint.update();System.out.println("going");}
            } else {
                while (pinpoint.getEncoderX()>x || pinpoint.getEncoderY()>y){pinpoint.update();System.out.println("going");}
            }
        }


        setPowerAll(0.0);
    }

    public void moveCoords(double[] currentPos, double[] newPos, double power){
        System.out.println("Move coordsing");

        double turnAngle=newPos[2] - currentPos[2];

        double x = newPos[0] - currentPos[0];
        double y = newPos[1] - currentPos[1];
        double heading = currentPos[2];

        //rotates the coordinates by heading degrees CW so the heading is effectively 0 and WAY easier to work with
        double[] rotatedCoordinates = {x*cos(-heading) - y*sin(-heading), x*sin(-heading) + y*cos(-heading)};

        double movementDistance = Math.hypot(x,y);
        double movementAngle = Math.asin(rotatedCoordinates[1] / movementDistance);

        System.out.println("Will move vector");
        boolean xDirection = newPos[0] > currentPos[0];
        boolean yDirection = newPos[1] > currentPos[1];
        moveVector(movementDistance, movementAngle, power, turnAngle , xDirection, yDirection);
        System.out.println("Moved Vector");
    }




}
