package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends LinearOpMode{

    Gamepad gamepad1=new Gamepad();
    Gamepad gamepad2=new Gamepad();
    Robot robot;
    public void runOpMode(){
        //Init
        robot= new Robot(hardwareMap);
        waitForStart();

        //What happens after "play" pressed on CH
        while(opModeIsActive()){
            robot.driveTrain.move(gamepad1);

            if(gamepad1.backWasPressed()){
                robot.driveTrain.parked=!robot.driveTrain.parked;
            }
            if(gamepad2.left_bumper) {
                robot.shooter.setPower(1.0);
            } else if (gamepad2.right_bumper) {
                robot.shooter.setPower(0.0);
            }

            if(gamepad2.left_trigger_pressed && (robot.shooter.getPower()!=0)){
                robot.shooter.setServoDegree(90);
            } else if (gamepad2.right_trigger_pressed || (robot.shooter.getPower()==0)){
                robot.shooter.setServoDegree(0);
            }

        }
    }

}
