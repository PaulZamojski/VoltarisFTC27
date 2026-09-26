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
        }
    }

}
