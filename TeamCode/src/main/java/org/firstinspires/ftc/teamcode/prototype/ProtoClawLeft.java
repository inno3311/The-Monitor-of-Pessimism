package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controller.ServoControl;

public class ProtoClawLeft extends ServoControl
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
}
