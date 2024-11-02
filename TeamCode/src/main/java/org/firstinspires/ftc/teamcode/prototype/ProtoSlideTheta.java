package org.firstinspires.ftc.teamcode.prototype;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.controller.MotorParent;

public class ProtoSlideTheta extends MotorParent
{

    public ProtoSlideTheta(LinearOpMode opMode)
    {
        super("slideTheta", true, true, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak, boolean slowMode)
    {
        super.analogControl(speedLimit, input, advanceBreak, slowMode);
    }

    @Override
    public Action action(int target, double speed)
    {
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
        PICKUP_WALL,
        INITIALIZATION
    }

    public void encoderPresets(Presets preset)
    {
        switch (preset)
        {
            case TOP_CHAMBER:
                super.encoderControl(-1165,0.5);
                break;
            case BOTTOM_CHAMBER:
                super.encoderControl(-525,0.5);
                break;
            case TOP_BUCKET:
                super.encoderControl(-2050,0.5);
                break;
            case BOTTOM_BUCKET:
                super.encoderControl(-2030,0.5);
                break;
            case PICKUP_FLOOR:
                super.encoderControl(0,1);
                break;
            case PICKUP_WALL:
                super.encoderControl(10,1);
                break;
            case INITIALIZATION:
                super.encoderControl(-1175,1);
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
