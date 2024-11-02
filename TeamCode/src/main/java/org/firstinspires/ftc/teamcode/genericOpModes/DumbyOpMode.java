package org.firstinspires.ftc.teamcode.genericOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.initialization.Initialization;
import org.firstinspires.ftc.teamcode.prototype.ProtoClaw;
import org.firstinspires.ftc.teamcode.prototype.ProtoLinearSlide;
import org.firstinspires.ftc.teamcode.prototype.ProtoSlideTheta;

@Autonomous(name = "dumbyOpMode")
public class DumbyOpMode extends LinearOpMode
{


    @Override
    public void runOpMode() throws InterruptedException
    {


        while (opModeIsActive());

//        linearSlide = new ProtoLinearSlide(this);
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
