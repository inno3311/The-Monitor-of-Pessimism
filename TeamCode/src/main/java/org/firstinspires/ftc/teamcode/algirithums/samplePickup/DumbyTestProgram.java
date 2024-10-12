package org.firstinspires.ftc.teamcode.algirithums.samplePickup;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.prototype.ProtoLinearSlide;
import org.firstinspires.ftc.teamcode.prototype.ProtoSlideTheta;

@Autonomous(name = "Failing")
public class DumbyTestProgram extends LinearOpMode
{

    MotorTicksConversion motorTicksConversion;
    DeltaChange deltaChange;
    ProtoLinearSlide linearSlide;
    ProtoSlideTheta slideTheta;

    @Override
    public void runOpMode() throws InterruptedException
    {
        linearSlide = new ProtoLinearSlide(this);
        slideTheta = new ProtoSlideTheta(this);
        motorTicksConversion = new MotorTicksConversion();
        deltaChange = new DeltaChange();

        waitForStart();

        while (opModeIsActive())
        {
            if (gamepad1.a)
            {
                linearSlide.encoderControl((int)(-1 * motorTicksConversion.linearSlideInCM() * deltaChange.armLength(70-48,1)), 0.3);
            }
//            if (gamepad1.y)
//            {
//                slideTheta.encoderControl((int) motorTicksConversion.ThetaInDegrees() * 45,0.3);
//            }
        }
    }
}
