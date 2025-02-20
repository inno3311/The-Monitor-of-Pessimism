package org.firstinspires.ftc.teamcode.initialization;

import com.qualcomm.robotcore.hardware.TouchSensor;

public class Initialization
{


    public Initialization()
    {

    }

    public void initialization()
    {
//        while ((!slideLimit.isPressed() || !elbowLimit.isPressed()))
//        {
//            retract();
//            slide.telemetry();
//            elbow.telemetry();
//        }
//
//        slide.resetEncoder();
//        elbow.resetEncoder();

        try {Thread.sleep(100);}
        catch (InterruptedException e) {throw new RuntimeException(e);}

        fitTheBox();
    }

    private void retract()
    {
//        slide.initialize(slideLimit, 1, 0.5);
//        elbow.initialize(elbowLimit, 1, 0.5);
//        claw.driveServo(0);
    }

    private void fitTheBox()
    {
//        elbow.encoderPresets(Elbow.Presets.INITIALIZATION);
    }

}
