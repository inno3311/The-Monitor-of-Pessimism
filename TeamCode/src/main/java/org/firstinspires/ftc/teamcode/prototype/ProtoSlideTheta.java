package org.firstinspires.ftc.teamcode.prototype;

import android.graphics.Path;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controller.MotorControl;

public class ProtoSlideTheta extends MotorControl
{
    protected ProtoSlideTheta(OpMode opMode)
    {
        super("slideTheta", true, false, opMode);
    }

    @Override
    protected void simpleDrive(double speed, boolean argument1, boolean argument2)
    {
        super.simpleDrive(speed, argument1, argument2);
    }
}
