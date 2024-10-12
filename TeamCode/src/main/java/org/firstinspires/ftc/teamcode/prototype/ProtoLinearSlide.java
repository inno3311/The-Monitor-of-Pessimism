package org.firstinspires.ftc.teamcode.prototype;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.controller.MotorParent;

public class ProtoLinearSlide extends MotorParent
{
    public ProtoLinearSlide(LinearOpMode opMode)
    {
        super("linearSlide", false, true, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak)
    {
        super.analogControl(speedLimit, input, advanceBreak);
    }

    @Override
    public Action action(int target, double speed) {
        return super.action(target, speed);
    }

    @Override
    protected void telemetry() {
        super.telemetry();
    }
}
