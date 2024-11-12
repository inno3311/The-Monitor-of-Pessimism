package org.firstinspires.ftc.teamcode.genericOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "dumbyOpMode")
public class DumbyOpMode extends LinearOpMode
{


    @Override
    public void runOpMode() throws InterruptedException
    {


        while (opModeIsActive());

//        linearSlide = new Slide(this);
//
//        waitForStart();
//
//        while (opModeIsActive())
//        {
//            if (gamepad1.dpad_up)
//            {
//                target += 10;
//            }
//            else if (gamepad1.dpad_down)
//            {
//                target -= 10;
//            }
//
//            if (gamepad1.y)
//            {
//                linearSlide.encoderControl(target,0.1);
//            }
//
////            linearSlide.telemetry();
//            telemetry.addData("Target", target);
//            telemetry.update();
//        }

    }
}
