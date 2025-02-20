package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MotorControllers.MotorParent;

public class Hanging extends MotorParent
{
    protected Hanging(LinearOpMode opMode)
    {
        super("Hanging", true, true, opMode);
    }

    public enum Presets
    {
        READY,
        HANG
    }

    public void presets(Presets preset)
    {
        switch (preset)
        {
            case READY:
                super.encoderControl(-920,1);
                break;
            case HANG:
                super.encoderControl(-375,1);
                break;
            default:
                break;
        }
    }
}
