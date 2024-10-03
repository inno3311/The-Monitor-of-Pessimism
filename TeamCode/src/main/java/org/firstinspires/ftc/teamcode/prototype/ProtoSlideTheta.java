package org.firstinspires.ftc.teamcode.prototype;

import android.graphics.Path;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controller.MotorControl;

public class ProtoSlideTheta extends MotorControl
{
    public ProtoSlideTheta(LinearOpMode opMode)
    {
        super("slideTheta", true, true, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak)
    {
        super.analogControl(speedLimit, input, advanceBreak);
    }




}
