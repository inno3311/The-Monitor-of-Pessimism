package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MotorControllers.MotorParent;

public class VSlides extends MotorParent
{
    public final int encoderBound = 0;

    protected VSlides(LinearOpMode opMode)
    {
        super("VSlides", true, true, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak, boolean slowMode, boolean upperLimit, int lowerLimit, boolean swapBounds)
    {
        super.analogControl(speedLimit, input, advanceBreak, slowMode, upperLimit, lowerLimit, swapBounds);
    }

    public enum Presets
    {
        TOP_CHAMBER,
        BOTTOM_CHAMBER,
        TOP_BUCKET,
        BOTTOM_BUCKET,
        PICKUP,
        TRANSFER_PICKUP,
        RETRACTED
    }

    public void presets(Presets preset)
    {
        switch (preset)
        {
            case TOP_CHAMBER:
                super.encoderControl(-920,1);
                break;
            case BOTTOM_CHAMBER:
                super.encoderControl(-375,1);
                break;
            case TOP_BUCKET:
                super.encoderControl(-2150,1);
                break;
            case BOTTOM_BUCKET:
                super.encoderControl(-920,1);
                break;
            case PICKUP:
                super.encoderControl(0,1);
                break;
            case TRANSFER_PICKUP:
                super.encoderControl(0,1);
                break;
            case RETRACTED:
                super.encoderControl(0,1);
                break;
            default:
                break;
        }
    }
}
