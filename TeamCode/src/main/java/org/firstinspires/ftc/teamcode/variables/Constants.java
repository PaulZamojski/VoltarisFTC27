package org.firstinspires.ftc.teamcode.variables;

public class Constants {


    //Units
    public static final double MM_PER_IN=25.4;
    public static final double IN_PER_MM=(1.0/MM_PER_IN);

    public static final double TICKS_PER_REVOLUTION=537.7; //312 RPM (19.2:1)

    public static final double KG_PER_LB=0.45359237;
    public static final double LBS_PER_KG=(1.0/KG_PER_LB);

    //Robot
    public static final double WHEEL_CIRCUMFERENCE=104*IN_PER_MM*2*Math.PI;

    //Scoring Elements
    public static final double POLLEN_DIAMETER=2.8;
    public static final double POLLEN_WEIGHT=0.055;

    public static final double NECTAR_DIAMETER=3.6;
    public static final double NECTAR_WEIGHT=0.091;

    //Field
    public static final double FIELD_LENGTH=144.0;
    public static final double TILE_SIZE_AVG=24.25;
    public static final double TILE_TAB_SIZE=1.0; //Guess
    public static final double TILE_BODY_SIZE=(144.0-5.0*TILE_TAB_SIZE)/6.0;

    public static final double HIVE_BOTTOM_HEIGHT=53.5;
    public static final double HIVE_RECT_HEIGHT=7.61;
    public static final double HIVE_TOTAL_HEIGHT=14;
    public static final double HIVE_WIDTH=20.0;

    //Coordinates (Red garden is 0,0 - same as pedropathing.com, Start=Lowest X-Coordinate of the several points - Y if tie)
    public static final double[] LOADING_AREA_RED_START={0.0,4.0*TILE_BODY_SIZE+4.0*TILE_TAB_SIZE};
    public static final double[] LOADING_AREA_RED_END={11.0,5.0*TILE_BODY_SIZE+4.0*TILE_TAB_SIZE};
    public static final double[] LOADING_AREA_BLUE_START={144.0-11.0,1.0*TILE_BODY_SIZE+1.0*TILE_TAB_SIZE};
    public static final double[] LOADING_AREA_BLUE_END={144.0,2.0*TILE_BODY_SIZE+1.0*TILE_TAB_SIZE};


}
