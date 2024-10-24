package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.fieldCentric.CentricDrive;
import org.firstinspires.ftc.teamcode.fieldCentric.TurnToHeading;

@TeleOp(name = "Prototype", group = "proto")
public class ProtoMaster extends LinearOpMode
{

    // DriveBase
    MechanicalDriveBase mechanicalDriveBase;
    TurnToHeading turnToHeading;
    CentricDrive centricDrive;
    IMUControl imu;

    // Accessories
    ProtoLinearSlide linearSlide;
    ProtoSlideTheta slideTheta;
    ProtoHang hang;
    ProtoWrist clawWrist;
    ProtoClaw claw;

    @Override
    public void runOpMode() throws InterruptedException
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        imu = new IMUControl(hardwareMap, telemetry);
        turnToHeading = new TurnToHeading(telemetry, mechanicalDriveBase, imu);
        centricDrive = new CentricDrive(mechanicalDriveBase, telemetry);
        linearSlide = new ProtoLinearSlide(this);
        slideTheta = new ProtoSlideTheta(this);
        hang = new ProtoHang(this);
        clawWrist = new ProtoWrist(this);
        claw = new ProtoClaw(this);

        waitForStart();

        while (opModeIsActive())
        {

            //        centricDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, imu.getAngle(), turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, 0.2, 0.2));

            mechanicalDriveBase.gamepadController(gamepad1);

            if (gamepad2.dpad_up)
            {
                linearSlide.encoderPresets(ProtoLinearSlide.Presets.TOP_CHAMBER);
                slideTheta.encoderPresets(ProtoSlideTheta.Presets.TOP_CHAMBER);
            }
            else if (gamepad2.dpad_down)
            {
                linearSlide.encoderPresets(ProtoLinearSlide.Presets.PICKUP_FLOOR);
                slideTheta.encoderPresets(ProtoSlideTheta.Presets.PICKUP_FLOOR);
            }
            else if (gamepad2.dpad_left)
            {
                linearSlide.encoderPresets(ProtoLinearSlide.Presets.TOP_BUCKET);
                slideTheta.encoderPresets(ProtoSlideTheta.Presets.TOP_BUCKET);
            }
            else if (gamepad2.dpad_right)
            {
                linearSlide.encoderPresets(ProtoLinearSlide.Presets.BOTTOM_BUCKET);
                slideTheta.encoderPresets(ProtoSlideTheta.Presets.BOTTOM_BUCKET);
            }
            else
            {
                linearSlide.analogControl(0.75, gamepad2.left_stick_y, true,false, 0,-2270);
                slideTheta.analogControl(1, gamepad2.right_stick_y, true, false);
            }

            hang.simpleDrive(1, gamepad2.y, gamepad2.a);

            if (gamepad2.right_bumper)
            {
                claw.driveServo(0);
            }
            else if (gamepad2.right_trigger > 0.2)
            {
                claw.driveServo(1);
            }

            if (gamepad2.left_bumper)
            {
                clawWrist.driveServo(0);
            }
            else if (gamepad2.left_trigger > 0.2)
            {
                clawWrist.driveServo(0.5);
            }

            linearSlide.telemetry();
            slideTheta.telemetry();
            hang.telemetry();
            telemetry.update();
        }

    }

}
