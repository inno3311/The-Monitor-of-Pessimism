package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.controller.MotorControl;

public class ProtoSlideTheta extends MotorControl
{

    public ProtoSlideTheta(LinearOpMode opMode)
    {
        super("slideTheta", true, true, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak)
    {
        super.analogControl(speedLimit, input, advanceBreak);
    }

    @Override
    public Action action(int target, double speed)
    {
        return super.action(target, speed);
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
                super.encoderControl(-1165,1);
                break;
            case BOTTOM_CHAMBER:
                super.encoderControl(-525,1);
                break;
            case TOP_BUCKET:
                super.encoderControl(-2050,1);
                break;
            case BOTTOM_BUCKET:
                super.encoderControl(-2030,1);
                break;
            case PICKUP_FLOOR:
                super.encoderControl(0,1);
                break;
            case PICKUP_WALL:
                super.encoderControl(10,1);
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
