package org.firstinspires.ftc.teamcode.algirithums.samplePickup;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.prototype.ProtoLinearSlide;
import org.firstinspires.ftc.teamcode.prototype.ProtoSlideTheta;
import org.firstinspires.ftc.teamcode.vision.SampleDetection;

@Autonomous(name = "Failing")
public class DumbyTestProgram extends LinearOpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    SampleDetection sampleDetection;
    MotorTicksConversion motorTicksConversion;
    DeltaChange deltaChange;
    ProtoLinearSlide linearSlide;
    ProtoSlideTheta slideTheta;

    @Override
    public void runOpMode() throws InterruptedException
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        sampleDetection = new SampleDetection(telemetry);
        linearSlide = new ProtoLinearSlide(this);
        slideTheta = new ProtoSlideTheta(this);
        motorTicksConversion = new MotorTicksConversion();
        deltaChange = new DeltaChange();

        waitForStart();

        while (opModeIsActive())
        {
            mechanicalDriveBase.gamepadController(gamepad1);

            sampleDetection.processFrame();

            if (gamepad1.right_bumper)
            {
                if (sampleDetection.z_distance() > 48)
                {
                    linearSlide.encoderControl((int) (-1 * motorTicksConversion.linearSlideInCM() * deltaChange.armLength(sampleDetection.z_distance() - 48, 1)), 0.3);
                }
            }
//            if (gamepad1.y)
//            {
//                slideTheta.encoderControl((int) motorTicksConversion.ThetaInDegrees() * 45,0.3);
//            }
        }
    }
}
