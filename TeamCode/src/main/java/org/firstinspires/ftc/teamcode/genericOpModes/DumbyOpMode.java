package org.firstinspires.ftc.teamcode.genericOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.prototype.ProtoLinearSlide;

@Autonomous(name = "dumbyOpMode")
public class DumbyOpMode extends LinearOpMode
{

    ProtoLinearSlide linearSlide;

    int target = 0;

    @Override
    public void runOpMode() throws InterruptedException
    {
        linearSlide = new ProtoLinearSlide(this);

        waitForStart();

        while (opModeIsActive())
        {
            if (gamepad1.dpad_up)
            {
                target += 10;
            }
            else if (gamepad1.dpad_down)
            {
                target -= 10;
            }

            if (gamepad1.y)
            {
                linearSlide.encoderControl(target,0.1);
            }

//            linearSlide.telemetry();
            telemetry.addData("Target", target);
            telemetry.update();
        }

    }
}
