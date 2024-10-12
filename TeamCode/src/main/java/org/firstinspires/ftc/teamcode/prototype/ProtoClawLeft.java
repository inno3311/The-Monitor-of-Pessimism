package org.firstinspires.ftc.teamcode.prototype;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.controller.ServoParent;

public class ProtoClawLeft extends ServoParent
{
    protected ProtoClawLeft(LinearOpMode opMode)
    {
        super("clawLeft", 0,0.5, opMode);
    }

    @Override
    protected void driveServo(double target)
    {
        super.driveServo(target);
    }

    @Override
    public Action action(double target)
    {
        return super.action(target);
    }
}
