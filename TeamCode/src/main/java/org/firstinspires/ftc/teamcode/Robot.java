package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.subsystems.*;
import org.opencv.core.Mat;

import com.qualcomm.hardware.limelightvision.Limelight3A;
import com.qualcomm.hardware.limelightvision.LLResult;

public class Robot {

    //Subsystems
    public DriveTrain driveTrain;
    public Shooter shooter;

    public Intake intake;

    public Limelight3A limelight;

     GoBildaPinpointDriver pinpoint;

    public Robot(HardwareMap hwMap){


        System.out.println("Robot def");
        pinpoint = hwMap.get(GoBildaPinpointDriver.class, "pinpoint");

        //Pinpoint init
        pinpoint.setOffsets(-0.6875, 0, DistanceUnit.INCH);
        pinpoint.setEncoderResolution(
                GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD
        );
        pinpoint.setEncoderDirections(
                GoBildaPinpointDriver.EncoderDirection.FORWARD,
                GoBildaPinpointDriver.EncoderDirection.FORWARD
        );

        pinpoint.resetPosAndIMU();
        pinpoint.initialize();

        driveTrain=new DriveTrain(hwMap, pinpoint);
        shooter=new Shooter(hwMap);
        intake = new Intake(hwMap);

        //limelight=hwMap.get(Limelight3A.class, "limelight");
        //limelight.pipelineSwitch(0);
    }
}
