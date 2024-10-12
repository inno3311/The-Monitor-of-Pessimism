package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.BuiltinCameraDirection;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.fieldCentric.CentricDrive;
import org.firstinspires.ftc.teamcode.fieldCentric.TurnToHeading;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp(name = "Prototype", group = "proto")
public class ProtoMaster extends LinearOpMode
{
    private static final boolean USE_WEBCAM = false;  // true for webcam, false for phone camera
    // DriveBase
    MechanicalDriveBase mechanicalDriveBase;
    TurnToHeading turnToHeading;
    CentricDrive centricDrive;
    IMUControl imu;

    // Accessories
    ProtoLinearSlide linearSlide;
    ProtoSlideTheta slideTheta;
    ProtoClawWrist clawWrist;

    ProtoClaw claw;

    private VisionPortal visionPortal;

    @Override
    public void runOpMode() throws InterruptedException
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        imu = new IMUControl(hardwareMap, telemetry);
        turnToHeading = new TurnToHeading(telemetry, mechanicalDriveBase, imu);
        centricDrive = new CentricDrive(mechanicalDriveBase, telemetry);
        linearSlide = new ProtoLinearSlide(this);
        slideTheta = new ProtoSlideTheta(this);
        clawWrist = new ProtoClawWrist(this);
        claw = new ProtoClaw(this);

        VisionPortal.Builder builder = new VisionPortal.Builder();

        // Set the camera (webcam vs. built-in RC phone camera).
        if (USE_WEBCAM) {
            builder.setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"));
        } else {
            builder.setCamera(BuiltinCameraDirection.BACK);
        }


        waitForStart();

        while (opModeIsActive())
        {


            //        centricDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, imu.getAngle(), turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, 0.2, 0.2));

            mechanicalDriveBase.gamepadController(gamepad1);

            linearSlide.analogControl(0.5, gamepad2.left_stick_y, false);

            slideTheta.analogControl(1, gamepad2.right_stick_y, false);

            if (gamepad2.right_bumper)
            {
                claw.driveServo(0.5);
            }
            else if (gamepad2.right_trigger > 0.2)
            {
                claw.driveServo(0);
            }

            if (gamepad2.left_bumper)
            {
                clawWrist.driveServo(1);
            }
            else if (gamepad2.left_trigger > 0.2)
            {
                clawWrist.driveServo(0.3);
            }


            linearSlide.telemetry();
            slideTheta.telemetry();
            telemetry.update();
        }

    }

}
