package org.firstinspires.ftc.teamcode.variables;
import org.firstinspires.ftc.teamcode.variables.Constants;
import org.firstinspires.ftc.teamcode.Pose;
public class RobotVariables {
    //Physical Specs
    public static final double SHOOTING_MOTOR_RPM=2000;

    public static final double WHEEL_CIRCUMFERENCE=104*Constants.IN_PER_MM*2*Math.PI;
    public static final double WHEEL_DISTANCE_FROM_CENTER=12; // TEMPORARY Fill in.

    //Auto
    //Poses
    public static final Pose STARTING_POSE_D = new Pose(56, 8.0, 270);
    public static final Pose BOTTOM_HIVE_SHOOTING = new Pose(56, 18, 270);

}
