package org.firstinspires.ftc.teamcode.outreach.Hippo;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controller.MotorParent;
import org.firstinspires.ftc.teamcode.controller.ServoParent;

public class HippoStomper extends ServoParent
{
    public HippoStomper(OpMode opMode)
    {
        super("stomper", 0, 1, opMode);
    }

    @Override
    protected void driveServo(double target)
    {
        super.driveServo(target);
    }
}
