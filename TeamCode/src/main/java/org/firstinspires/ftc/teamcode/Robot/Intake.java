package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MotorControllers.CRServoParent;

public class Intake extends CRServoParent
{
    protected Intake(LinearOpMode opMode)
    {
        super("Intake", opMode);
    }

    @Override
    protected void driveServoBoolean(boolean forward, boolean backward)
    {
        super.driveServoBoolean(forward, backward);
    }
}
