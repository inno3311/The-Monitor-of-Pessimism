package org.firstinspires.ftc.teamcode.prototype;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.controller.MotorControl;

public class ProtoLinearSlide extends MotorControl
{

    public ProtoLinearSlide(LinearOpMode opMode)
    {
        super("linearSlide", true, true, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak)
    {
        super.analogControl(speedLimit, input, advanceBreak);
    }

    public enum Presets
    {
        TOP_CHAMBER,
        BOTTOM_CHAMBER,
        TOP_BUCKET,
        BOTTOM_BUCKET,
        PICKUP_FLOOR,
        PICKUP_WALL
    }

    public void encoderPresets(Presets preset)
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
                super.encoderControl(-2270,1);
                break;
            case BOTTOM_BUCKET:
                super.encoderControl(-1 * 920,1);
                break;
            case PICKUP_FLOOR:
                super.encoderControl(0,1);
                break;
            case PICKUP_WALL:
                super.encoderControl(1,1);
                break;
            default:
                break;
        }
    }

    @Override
    protected void telemetry()
    {
        super.telemetry();
    }
}
