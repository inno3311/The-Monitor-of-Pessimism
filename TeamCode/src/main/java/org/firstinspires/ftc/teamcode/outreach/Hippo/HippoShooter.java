package org.firstinspires.ftc.teamcode.outreach.Hippo;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controller.MotorParent;

public class HippoShooter extends MotorParent
{
    // + speed is shooting
    public HippoShooter(LinearOpMode opMode)
    {
        super("shooter", true, false, opMode);
    }

    @Override
    protected void run(double speed)
    {
        super.run(speed);
    }
}
