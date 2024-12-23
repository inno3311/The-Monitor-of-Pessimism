package org.firstinspires.ftc.teamcode.initialization;

import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Robot.Elbow;
import org.firstinspires.ftc.teamcode.Robot.Slide;

public class Initialization
{
    Slide slide;
    TouchSensor slideLimit;
    Elbow elbow;
    TouchSensor elbowLimit;

    public Initialization(Slide slide, TouchSensor slideLimit, Elbow elbow, TouchSensor elbowLimit)
    {
        this.slide = slide;
        this.slideLimit = slideLimit;
        this.elbow = elbow;
        this.elbowLimit = elbowLimit;

    }

    public void initialization()
    {
        while ((!slideLimit.isPressed() || !elbowLimit.isPressed()))
        {
            retract();
            slide.telemetry();
            elbow.telemetry();
        }

        slide.resetEncoder();
        elbow.resetEncoder();

        try {Thread.sleep(100);}
        catch (InterruptedException e) {throw new RuntimeException(e);}

        fitTheBox();
    }

    private void retract()
    {
        slide.initialize(slideLimit, 1, 0.5);
        elbow.initialize(elbowLimit, 1, 0.5);
    }

    private void fitTheBox()
    {
        elbow.encoderPresets(Elbow.Presets.INITIALIZATION);
    }

}
