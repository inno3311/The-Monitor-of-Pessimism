package org.firstinspires.ftc.teamcode.initialization;

import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.prototype.Elbow;
import org.firstinspires.ftc.teamcode.prototype.Slide;

public class Initialization
{
    Slide slide;
    TouchSensor slideLimit;
    Elbow theta;
    TouchSensor thetaLimit;

    public Initialization(Slide slide, TouchSensor slideLimit, Elbow theta, TouchSensor thetaLimit)
    {
        this.slide = slide;
        this.slideLimit = slideLimit;
        this.theta = theta;
        this.thetaLimit = thetaLimit;

    }

    public void initialization()
    {
        while ((!slideLimit.isPressed() || !thetaLimit.isPressed()))
        {
            retract();
            slide.telemetry();
            theta.telemetry();
        }

        slide.resetEncoder();
        theta.resetEncoder();

        try {Thread.sleep(100);}
        catch (InterruptedException e) {throw new RuntimeException(e);}

        fitTheBox();
    }

    private void retract()
    {
        slide.initialize(slideLimit, 1, 0.25);
        theta.initialize(thetaLimit, 1, 0.5);
    }

    private void fitTheBox()
    {
        theta.encoderPresets(Elbow.Presets.INITIALIZATION);
    }

}
