package org.firstinspires.ftc.teamcode.prototype;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.controller.ServoParent;

public class Claw extends ServoParent
{
    public Claw(LinearOpMode opMode)
    {
        super("claw", 0,1, opMode);
    }

    @Override
    public void driveServo(double target)
    {
        super.driveServo(target);
    }

    @Override
    public Action action(double target)
    {
        return super.action(target);
    }
}
