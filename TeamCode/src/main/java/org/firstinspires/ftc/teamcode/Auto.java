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

    double[] currentPos = RobotVariables.AUTO_START;
    double[] newPos;

    boolean topHive = false;


    public void runOpMode() {
        waitForStart();

        robot.limelight.start();
        //Starting Steps


        //Main loop
        while (opModeIsActive()) {


        }
    }

    public void runSteps(double[][] autoSteps) {
        //  id,
        // id="0"(move):x,y,heading,power;
        // id="1"(wait):time(int ms);
        //id="2"(run shoot sequence): int length ms, double shooter power
        // id="2.1"(set shooter power):power
        // id="2.2"(shooter.allow balls): int allowBalls (1=true, 0=false)
        for (double[] step : autoSteps) {
            switch ((int) step[0]) {
                case 0: //Move
                    newPos = currentPos = Arrays.copyOfRange(step, 1, 4);
                    robot.driveTrain.moveCoords(currentPos, newPos, step[4]);
                    currentPos = newPos;
                    break;

                case 1: //Wait
                    try {
                        Thread.sleep((long) step[1]);
                    } catch (InterruptedException e) {
                    }
                    break;

                case 2:
                    if (step[0] == 2.0) { //Full shooting sequence (id,time,power)
                        robot.shooter.setPower(step[2]);
                        robot.shooter.allowBalls(true);

                        try {
                            Thread.sleep((long) step[1]);
                        } catch (InterruptedException e) {
                        }

                        robot.shooter.allowBalls(false);
                        robot.shooter.setPower(0);

                    } else if (step[0] == 2.1) {//Set shooter power
                        robot.shooter.setPower(step[2]);
                    } else if (step[0] == 2.2) { //changes allowBalls
                        if (step[1] == 1.0) {
                            robot.shooter.allowBalls(true);
                        } else if (step[1] == 2.0) {
                            robot.shooter.allowBalls(false);
                        }
                    }
                    break;
            }
        }

    }
}