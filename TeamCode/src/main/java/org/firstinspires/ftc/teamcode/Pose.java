package org.firstinspires.ftc.teamcode;

public class Pose{

    public double x;
    public double y;
    public double heading;

    public Pose(double x, double y, double heading){
        this.x = x;
        this.y = y;
        this.heading = heading;
    }

    public void main(String[] args) {
        Pose startingPoseD = new Pose(56, 8.0, 270);
        Pose bottomHiveShooting = new Pose(56,18,270);
    }
}