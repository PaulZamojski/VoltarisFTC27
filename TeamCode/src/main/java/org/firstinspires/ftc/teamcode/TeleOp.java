package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends LinearOpMode{

    //Vars
    boolean fieldOriented=false;

    double shootingVelocity = 1860;

    boolean shooting=false;

    Robot robot;
    public void runOpMode(){
        //Init
        System.out.println("Op mode start");
        robot = new Robot(hardwareMap);
        System.out.println("Robot made");
        waitForStart();

        //What happens after "play" pressed on CH
        while(opModeIsActive()){
            //telemetry.addData("Drivetrain Power",robot.driveTrain.getPowerMultiplier());
            telemetry.addData("Field oriented?",fieldOriented);
            telemetry.addData("Parked?",robot.driveTrain.parked);
            telemetry.addData("Bot Angle:", -robot.driveTrain.getDirection());

            //Driving
            robot.driveTrain.move(gamepad1, fieldOriented);

            if(gamepad1.backWasPressed()){
                robot.driveTrain.parked=!robot.driveTrain.parked;
            }
            if(gamepad1.leftTriggerWasPressed()){
                fieldOriented=true;
            } else if (gamepad1.rightTriggerWasPressed()){
                fieldOriented=false;
            }


            /*** if (Math.abs(gamepad1.right_stick_y)>=.5) {
                robot.driveTrain.powerMultiplier -= gamepad1.left_stick_y * 0.1;//Value tbd
                if (robot.driveTrain.getPowerMultiplier() > 1.0) {
                    robot.driveTrain.powerMultiplier=1.0;
                }
            }
            if(robot.driveTrain.getPowerMultiplier()<=.3){
                robot.driveTrain.setPowerMultiplier(.3);
            }
            if (gamepad1.y){
                robot.driveTrain.setPowerMultiplier(1.0);
            }
             **/

            //Controller 2
            telemetry.addData("\n\n\n","\n\n\n");
            telemetry.addData("Shooting Velocity:", shootingVelocity);
            telemetry.addData("Actual Shooter Speed:", robot.shooter.getShooterVelocity());
            telemetry.addData("Intake Power:", robot.intake.getIntakePower());

            telemetry.update();

            if (shooting) {robot.shooter.setShooterVelocity(shootingVelocity);}

            if(gamepad2.left_bumper) {
                robot.shooter.setShooterVelocity(shootingVelocity);
            } else if (gamepad2.right_bumper) {
                robot.shooter.setShooterVelocity(0.0);
                shooting=false;
            }
            if (Math.abs(gamepad2.left_stick_y) >= .3){
                shootingVelocity-=1*gamepad2.left_stick_y;
                if (shooting){robot.shooter.setShooterVelocity(shootingVelocity);}
            }


            if(gamepad2.leftTriggerWasPressed() && (robot.shooter.getPower()!=0)){
                robot.shooter.allowBalls();
            }

            if (gamepad2.aWasPressed()){
                robot.intake.setIntakePower(1.0);
            }else if (gamepad2.bWasPressed()){
                robot.intake.setIntakePower(0);
            } else if (gamepad2.yWasPressed()){
                robot.intake.setIntakePower(-1);
            }

        }
    }

}
