package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.variables.Constants;

@Autonomous (name = "auto")
public class Auto extends LinearOpMode{
    Robot robot=new Robot(hardwareMap);

    public void runOpMode(){
        waitForStart();

        //Starting moves

        //Main loop
        while(opModeIsActive()){









            move();
            intakeOn();
            while (robot.driveTrain.lf.isBusy()){

            }



        }
    }


}
