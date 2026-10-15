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
    }

    public double[] getPos(){
        return new double[]{x, y, heading};
    }
}