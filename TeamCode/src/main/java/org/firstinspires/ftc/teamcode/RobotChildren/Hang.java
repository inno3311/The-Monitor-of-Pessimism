package org.firstinspires.ftc.teamcode.RobotChildren;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.controller.MotorParent;

public class Hang extends MotorParent
{

    public Hang(LinearOpMode opMode)
    {
        super("hang", true, true, opMode);
    }

    @Override
    protected void simpleDrive(double speed, boolean argument1, boolean argument2)
    {
        super.simpleDrive(speed, argument1, argument2);
    }

    public enum Presets
    {
        READY,
        SET,
        GO,
    }

    public void encoderPresets(Presets preset)
    {
        switch (preset)
        {
            case READY:
                super.encoderControl(1,1);
                break;
            case SET:
                super.encoderControl(1,1);
                break;
            case GO:
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
