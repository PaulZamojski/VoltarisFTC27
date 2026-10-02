package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.subsystems.DriveTrain;
import org.firstinspires.ftc.teamcode.variables.Constants;
import org.firstinspires.ftc.teamcode.variables.RobotVariables;

@Autonomous (name = "auto")
public class Auto extends LinearOpMode{
    Robot robot=new Robot(hardwareMap);
    double[] currentPos;

    public void setPos(double[] newPos){
        currentPos[0]=newPos[0];
        currentPos[1]=newPos[1];
        currentPos[2]=newPos[2];
    }

    public void runOpMode(){
        waitForStart();

        double[] currentPos=RobotVariables.AUTO_START.clone();
        double currentAngle=270.0;

        //Starting moves (Starts BACKWARDS - Facing 270)
        robot.driveTrain.moveCoords(currentPos, RobotVariables.AUTO_NODE1);
        currentPos

        robot.shooter.setPower(1.0);
        robot.shooter.allowBalls(true);
        Thread.sleep(2000); //Tenative
        robot.shooter.allowBalls(false);

        //Main loop
        while(opModeIsActive()){









            move();
            intakeOn();
            while (robot.driveTrain.lf.isBusy()){

            }



        }
    }


}
