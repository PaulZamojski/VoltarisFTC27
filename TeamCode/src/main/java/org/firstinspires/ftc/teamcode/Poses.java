package org.firstinspires.ftc.teamcode;

public class Poses {
    double x;
    double y;
    double heading;
    Poses(double x, double y, double heading){
        this.x=x;
        this.y=y;
        this.heading=heading;
    }

    public double[] getPos(){
        double[] pos={x,y,heading};
        return pos;

    }

}
