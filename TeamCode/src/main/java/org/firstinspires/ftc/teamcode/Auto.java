package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.variables.Constants;
import org.firstinspires.ftc.teamcode.variables.RobotVariables;

import java.lang.reflect.Array;
import java.util.Arrays;

import org.firstinspires.ftc.teamcode.Pose;

@Autonomous (name = "auto")
public class Auto extends LinearOpMode {
    Robot robot = new Robot(hardwareMap);

    double[] currentPos = RobotVariables.AUTO_START;
    double[] newPos;

    boolean topHive = false;


    public void runOpMode() {
        waitForStart();

        //robot.limelight.start();
        //Starting Steps


        //Main loop
        while (opModeIsActive()) {


        }
    }
}