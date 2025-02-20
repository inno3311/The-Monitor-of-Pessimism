package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MotorControllers.ServoParent;

public class Transfer extends ServoParent
{
    protected Transfer(LinearOpMode opMode)
    {
        super("Transfer", 0, 1, opMode);
    }

    @Override
    protected void driveServo(double target, boolean argument)
    {
        super.driveServo(target, argument);
    }
}
