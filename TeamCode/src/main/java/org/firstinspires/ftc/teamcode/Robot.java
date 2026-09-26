package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    public DriveTrain driveTrain;

    public Robot(HardwareMap hwMap){
        driveTrain=new DriveTrain(hwMap);
    }
}
