package org.firstinspires.ftc.teamcode.prototype;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.controller.MotorParent;

public class Slide extends MotorParent
{

    public Slide(LinearOpMode opMode)
    {
        super("slide", true, true, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak, boolean slowMode, boolean lowerLimit, int upperLimit, boolean swapBounds)
    {
        super.analogControl(speedLimit, input, advanceBreak, slowMode, lowerLimit, upperLimit, swapBounds);
    }

    @Override
    public Action action(int target, double speed) {
        return super.action(target, speed);
    }

    @Override
    public void initialize(TouchSensor sensor, int direction, double speed) {super.initialize(sensor, direction, speed);}

    @Override
    public void resetEncoder() {super.resetEncoder();}

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
                super.encoderControl(-2150,1);
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
    public void telemetry()
    {
        super.telemetry();
    }
}
