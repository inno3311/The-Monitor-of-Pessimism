package org.firstinspires.ftc.teamcode.outreach.Hippo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MotorControllers.ServoParent;

public class HippoTrigger extends ServoParent
{
    // the smalled the value the more the trigger will be extended, the larger the more it will be contracted
    public HippoTrigger(LinearOpMode opMode)
    {
        super("trigger", 0, 1, opMode);
    }

    @Override
    protected void driveServo(double target)
    {
        super.driveServo(target);
    }
}
