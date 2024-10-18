package org.firstinspires.ftc.teamcode.prototype;

import com.acmerobotics.roadrunner.Line;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.controller.MotorControl;

public class ProtoHang extends MotorControl
{

    public ProtoHang(LinearOpMode opMode)
    {
        super("hang", true, false, opMode);
    }

    @Override
    protected void simpleDrive(double speed, boolean argument1, boolean argument2)
    {
        super.simpleDrive(speed, argument1, argument2);
    }
}
