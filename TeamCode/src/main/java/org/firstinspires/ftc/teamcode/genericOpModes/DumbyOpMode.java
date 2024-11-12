package org.firstinspires.ftc.teamcode.genericOpModes;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.AutoBucket;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.aprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.controller.DriveController;
import org.firstinspires.ftc.teamcode.initialization.Initialization;
import org.firstinspires.ftc.teamcode.prototype.Claw;
import org.firstinspires.ftc.teamcode.prototype.Elbow;
import org.firstinspires.ftc.teamcode.prototype.Slide;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@Autonomous(name = "dumbyOpMode")
public class DumbyOpMode extends LinearOpMode
{
    DriveController driveController;
    IMUControl imu;
    Initialization initialization;
    AprilTagMaster aprilTag;
    Claw claw;
    Slide slide;
    TouchSensor slideLimit;
    Elbow elbow;
    TouchSensor elbowLimit;
    MecanumDrive drive;
    AutoBucket autoBucket;

    @Override
    public void runOpMode() throws InterruptedException
    {
        driveController = new DriveController(hardwareMap);

        imu = new IMUControl(hardwareMap, telemetry);

        slide = new Slide(this);
        slideLimit = hardwareMap.get(TouchSensor.class, "slideLimit");
        elbow = new Elbow(this);
        elbowLimit = hardwareMap.get(TouchSensor.class, "elbowLimit");

        claw = new Claw(this);

        initialization = new Initialization(slide, slideLimit, elbow, elbowLimit);

        aprilTag = new AprilTagMaster(hardwareMap);

        initialization.initialization();

        waitForStart();

        while (opModeIsActive())
        {
            driveController.gamepadController(gamepad1);

            if (aprilTag.aprilTagDetected()) {telemetry.addData("Heading", aprilTag.getFieldYaw());}
//            aprilTag.tagsTelemetry(telemetry);
            if (gamepad1.a && aprilTag.getDetectionID() == 16)
            {
                autoBucket = new AutoBucket(new MecanumDrive(hardwareMap, new Pose2d(aprilTag.getFieldX(), aprilTag.getFieldY(), Math.toRadians(aprilTag.getFieldYaw()))), slide, elbow, claw);
                autoBucket.bucketRun(aprilTag.getFieldX(), aprilTag.getFieldY(), Math.toRadians((aprilTag.getFieldYaw())));
            }
            telemetry.update();

        }





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
