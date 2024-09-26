package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Prototype", group = "proto")
public class ProtoMaster extends OpMode
{

    ProtoLinearSlide linearSlide;
    ProtoSlideTheta slideTheta;
    ProtoClawWrist clawWrist;
    ProtoClawLeft clawLeft;
    ProtoClawRight clawRight;


    @Override
    public void init()
    {
        linearSlide = new ProtoLinearSlide(this);
        slideTheta = new ProtoSlideTheta(this);
        clawWrist = new ProtoClawWrist(this);
        clawLeft = new ProtoClawLeft(this);
        clawRight = new ProtoClawRight(this);

    }

    @Override
    public void loop()
    {
        linearSlide.analogControl(0.5, gamepad2.left_stick_y, true);
        slideTheta.simpleDrive(0.5, gamepad2.y, gamepad2.a);

        if (gamepad2.right_bumper)
        {
            clawLeft.driveServo(0);
            clawRight.driveServo(0);
        }
        else if (gamepad2.right_trigger > 0.2)
        {
            clawLeft.driveServo(0.5);
            clawRight.driveServo(0.5);
        }

        if (gamepad2.left_bumper)
        {
            clawWrist.driveServo(0);
        }
        else if (gamepad2.right_trigger > 0.2)
        {
            clawWrist.driveServo(0.5);
        }

    }
}
