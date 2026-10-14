package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends LinearOpMode{

    //Vars
    boolean fieldOriented=false;

    Robot robot;
    public void runOpMode(){
        //Init
        System.out.println("Op mode start");
        robot= new Robot(hardwareMap);
        System.out.println("Robot made");
        waitForStart();

        //What happens after "pla y" pressed on CH
        while(opModeIsActive()){
            telemetry.addData("Field oriented?",fieldOriented);
            telemetry.addData("Parked?",robot.driveTrain.parked);
            telemetry.addData("Bot Angle:", -robot.driveTrain.getDirection());

            telemetry.update();

            //Driving
            robot.driveTrain.move(gamepad1, fieldOriented);

            if(gamepad1.backWasPressed()){
                robot.driveTrain.parked=!robot.driveTrain.parked;
            }
            if(gamepad1.left_stick_button){
                fieldOriented=!fieldOriented;
            }

            //Shooting
            if(gamepad2.left_bumper) {
                robot.shooter.setPower(1.0);
            } else if (gamepad2.right_bumper) {
                robot.shooter.setPower(0.0);
            }
            //if(gamepad2.left_trigger_pressed && (robot.shooter.getPower()!=0)){
            //    robot.shooter.setServoDegree(90);
            //} else if (gamepad2.right_trigger_pressed || (robot.shooter.getPower()==0)){
            //    robot.shooter.setServoDegree(0);
            //}

        }
    }

}
