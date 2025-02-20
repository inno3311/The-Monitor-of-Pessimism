package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MotorControllers.MotorParent;

public class HSlides extends MotorParent
{
    public final int encoderBound = 0;

    protected HSlides(LinearOpMode opMode)
    {
        super("HSlides", true, true, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak, boolean slowMode, boolean upperLimit, int lowerLimit, boolean swapBounds)
    {
        super.analogControl(speedLimit, input, advanceBreak, slowMode, upperLimit, lowerLimit, swapBounds);
    }

    public enum Presets
    {
        RETRACTED
    }

    public void presets(Presets preset)
    {
        switch (preset)
        {
            case RETRACTED:
                super.encoderControl(-920,1);
                break;
            default:
                break;
        }
    }
}
