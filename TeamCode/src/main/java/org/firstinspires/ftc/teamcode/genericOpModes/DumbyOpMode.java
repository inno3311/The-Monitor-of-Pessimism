package org.firstinspires.ftc.teamcode.genericOpModes;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.teamcode.AutoBucket;
import org.firstinspires.ftc.teamcode.aprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.controller.DriveController;
import org.firstinspires.ftc.teamcode.initialization.Initialization;
import org.firstinspires.ftc.teamcode.RobotChildren.Slide;
import org.firstinspires.ftc.teamcode.RobotChildren.Elbow;
import org.firstinspires.ftc.teamcode.RobotChildren.Hang;
import org.firstinspires.ftc.teamcode.RobotChildren.Wrist;
import org.firstinspires.ftc.teamcode.RobotChildren.Claw;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@Autonomous(name = "dumbyOpMode")
@Disabled
public class DumbyOpMode extends LinearOpMode
{
    DriveController driveController;
    Initialization initialization;
    AprilTagMaster aprilTag;
    Claw claw;
    Slide slide;
    TouchSensor slideLimit;
    Wrist wrist;
    Elbow elbow;
    TouchSensor elbowLimit;
    MecanumDrive drive;
    AutoBucket autoBucket;

    @Override
    public void runOpMode() throws InterruptedException
    {
        driveController = new DriveController(hardwareMap);

        slide = new Slide(this);
        slideLimit = hardwareMap.get(TouchSensor.class, "slideLimit");
        elbow = new Elbow(this);
        elbowLimit = hardwareMap.get(TouchSensor.class, "elbowLimit");

        wrist = new Wrist(this);
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
                autoBucket = new AutoBucket(new MecanumDrive(hardwareMap, new Pose2d(aprilTag.getFieldX(), aprilTag.getFieldY(), Math.toRadians(aprilTag.getFieldYaw()))), slide, elbow, wrist, claw);
                autoBucket.bucketRun(aprilTag.getFieldX(), aprilTag.getFieldY(), Math.toRadians((aprilTag.getFieldYaw())), aprilTag.getDetectionID());
            }
            telemetry.update();

        }

    }
}
