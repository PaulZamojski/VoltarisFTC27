package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.variables.Constants;
import org.firstinspires.ftc.teamcode.variables.RobotVariables;

import java.lang.reflect.Array;
import java.util.Arrays;

@Autonomous (name = "auto")
public class Auto extends LinearOpMode{
    Robot robot=new Robot(hardwareMap);

    double[] currentPos=RobotVariables.AUTO_START;
    double[] newPos;

    //Starting Steps
    double[][] autoSteps={//  id,
            // id="0"(move):x,y,heading,power;
            // id="1"(wait):time(int ms);
            // id="2"(set shooter power):power
        {0, 56.0, 18.0, 270.0,1.0},
        {2,1.0},
        {1, 2000}
    };


    public void runOpMode(){
        waitForStart();

        //Interpreting Starting Steps
        for (double[] step : autoSteps) {
            switch ((int) step[0]) {
                case 0: //Move
                    newPos = currentPos = Arrays.copyOfRange(step, 1, 4);
                    robot.driveTrain.moveCoords(currentPos, newPos, step[4]);
                    currentPos = newPos;
                    break;

                case 1: //Wait
                    try {Thread.sleep((long)step[1]);}catch (InterruptedException e){}
                    break;

                case 2: //Set shooter power
                    robot.shooter.setPower(step[2]);
                    break;
            }
        }

        //Main loop
        while(opModeIsActive()){



        }
    }


}
