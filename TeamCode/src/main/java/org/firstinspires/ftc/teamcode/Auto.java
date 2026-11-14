package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.*;


import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Shooter;
import org.firstinspires.ftc.teamcode.variables.Constants;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.variables.RobotVariables;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.lang.reflect.Array;
import java.util.Arrays;



@Autonomous (name = "auto")
public class Auto extends LinearOpMode {
    Robot robot;


    boolean topHive = false;

    //Poses
    private final Poses STARTING_POSE_D = new Poses(56,9,270);
    private final Poses BOTTOM_HIVE_SHOOTING = new Poses(56, 9, 270);
    private final Poses TOP_HIVE_SHOOTING = new Poses(56,134,0);
    private final Poses RED_GARDEN_START = new Poses(18,9,180);
    private final Poses RED_BOTTOM_CORNER = new Poses(10,8,180);
    private final Poses RED_FLOWER = new Poses(8,270,270);
    private final Poses PARKED = new Poses(8,105,90);

    final boolean bottomHiveStart=false;


    public void runOpMode() throws InterruptedException {
        robot = new Robot(hardwareMap);

        waitForStart();

        if (bottomHiveStart) {

            moveCoords(STARTING_POSE_D.getPos(), BOTTOM_HIVE_SHOOTING.getPos(), .5);
            int SHOOTING_VELOCITY = 1815;
            robot.shooter.runShootingSequence(SHOOTING_VELOCITY);

            moveCoords(BOTTOM_HIVE_SHOOTING.getPos(), RED_GARDEN_START.getPos(), .5);
            robot.intake.setIntakePower(1.0);
            moveCoords(RED_GARDEN_START.getPos(), RED_BOTTOM_CORNER.getPos(), .5);
            sleep(50);
            robot.intake.setIntakePower(0);

            moveCoords(RED_BOTTOM_CORNER.getPos(), BOTTOM_HIVE_SHOOTING.getPos(), 1);
            robot.shooter.runShootingSequence(SHOOTING_VELOCITY);

            moveCoords(BOTTOM_HIVE_SHOOTING.getPos(), PARKED.getPos(), .5);
        }
        robot.driveTrain.moveVector(15, Math.PI/2, .5, 0);

        while (opModeIsActive()) {


        }
    }
    public void sleep(int milliseconds){
        try {
            sleep(milliseconds);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public void moveCoords(double[] currentPos, double[] newPos, double power){System.out.println("Gonna call move");robot.driveTrain.moveCoords(currentPos, newPos, power);}
}

