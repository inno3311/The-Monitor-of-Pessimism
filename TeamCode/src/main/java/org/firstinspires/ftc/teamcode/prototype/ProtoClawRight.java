package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controller.ServoControl;

public class ProtoClawRight extends ServoControl
{
    protected ProtoClawRight(OpMode opMode)
    {
        super("clawRight", 0, 0.5, opMode);
    }

    @Override
    protected void driveServo(double target)
    {
        super.driveServo(target);
    }
}
