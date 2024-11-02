package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.controller.ServoControl;

public class ProtoWrist extends ServoControl
{

    protected ProtoWrist(LinearOpMode opMode)
    {
        super("wrist", 0,0.5, opMode);
    }

    @Override
    protected void driveServo(double target)
    {
        super.driveServo(target);
    }
}
