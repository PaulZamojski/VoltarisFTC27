package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name="TeleOp")
public class TeleOp extends LinearOpMode{

    //Gamepad gamepad1=new Gamepad();
    //Gamepad gamepad2=new Gamepad();
    Robot robot;
    public void runOpMode(){
        //Init
        System.out.println("Op mode start");
        robot= new Robot(hardwareMap);
        System.out.println("Robot made");
        waitForStart();

        //What happens after "pla y" pressed on CH
        while(opModeIsActive()){
            telemetry.addData("x",gamepad1.left_stick_x);
            telemetry.addData("y",gamepad1.left_stick_y);
            telemetry.addData("rx",gamepad1.right_stick_x);

            telemetry.update();

            robot.driveTrain.move(gamepad1);

            if(gamepad1.backWasPressed()){
                robot.driveTrain.parked=!robot.driveTrain.parked;
            }
            if (gamepad1.right_trigger_pressed){
                robot.driveTrain.setPower(0.1, 0.1, 0.1, 0.1);
            }
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
