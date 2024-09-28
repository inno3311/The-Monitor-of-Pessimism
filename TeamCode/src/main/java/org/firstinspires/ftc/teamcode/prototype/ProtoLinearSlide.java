package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controller.MotorControl;

public class ProtoLinearSlide extends MotorControl
{
    protected ProtoLinearSlide(OpMode opMode)
    {
        super("linearSlide", false, false, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak)
    {
        super.analogControl(speedLimit, input, advanceBreak);
    }
}
