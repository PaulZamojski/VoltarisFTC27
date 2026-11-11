package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

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

    IMU imu;

    public Robot(HardwareMap hwMap){


        System.out.println("Robot def");
        IMU imu = hwMap.get(IMU.class, "imu");
        System.out.println("IMU Gotten");
        //Adjust the orientation parameters to match your robot
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.UP,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        driveTrain=new DriveTrain(hwMap, imu);
        shooter=new Shooter(hwMap);
        intake = new Intake(hwMap);

        //limelight=hwMap.get(Limelight3A.class, "limelight");
        //limelight.pipelineSwitch(0);
    }
}
