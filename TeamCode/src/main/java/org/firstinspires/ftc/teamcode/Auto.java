package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.variables.Constants;
import org.firstinspires.ftc.teamcode.variables.RobotVariables;

import java.lang.reflect.Array;
import java.util.Arrays;



@Autonomous (name = "auto")
public class Auto extends LinearOpMode {
    Robot robot = new Robot(hardwareMap);

    boolean topHive = false;


    public void runOpMode() {
        waitForStart();

        //robot.limelight.start();
        //Starting Steps
        moveCoords(RobotVariables.STARTING_POSE_D.getPos(), RobotVariables.BOTTOM_HIVE_SHOOTING.getPos(), 0.5);
        moveCoords(RobotVariables.BOTTOM_HIVE_SHOOTING.getPos(), RobotVariables.STARTING_POSE_D.getPos(), 0.5);


        //Main loop
        while (opModeIsActive()) {


        }
    }
    public void moveCoords(double[] currentPos, double[] newPos, double power){robot.driveTrain.moveCoords(currentPos, newPos, power);}
}