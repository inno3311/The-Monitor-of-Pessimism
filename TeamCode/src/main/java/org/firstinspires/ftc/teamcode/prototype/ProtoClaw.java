package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.controller.ServoControl;

public class ProtoClaw extends ServoControl
{
    protected ProtoClaw(LinearOpMode opMode)
    {
        super("claw", 0,1, opMode);
    }

    @Override
    protected void driveServo(double target)
    {
        super.driveServo(target);
    }
}
