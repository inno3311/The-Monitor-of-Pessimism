package org.firstinspires.ftc.teamcode.Robot;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.aprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.fieldCentric.CentricDrive;
import org.firstinspires.ftc.teamcode.fieldCentric.TurnToHeading;
import org.firstinspires.ftc.teamcode.initialization.Initialization;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOp", group = "proto")
public class TeleOp extends LinearOpMode
{
    // Sensors
    AprilTagMaster aprilTag;
    IMUControl imu;


    // Algorithms
    Initialization initialization;

    // DriveBase
    MecanumDrive drive;
    TurnToHeading turnToHeading;
    CentricDrive centricDrive;

    // Accessories
   HSlides hSlides;
   VSlides vSlides;
   Transfer transfer;
   Intake intake;

    //Other
    ElapsedTime time;


    @Override
    public void runOpMode() throws InterruptedException
    {
        aprilTag = new AprilTagMaster(hardwareMap);
        imu = new IMUControl(hardwareMap, telemetry);

        drive = new MecanumDrive(hardwareMap, null);
        turnToHeading = new TurnToHeading(telemetry, drive, imu);
        centricDrive = new CentricDrive(drive, telemetry);

        hSlides = new HSlides(this);
        vSlides = new VSlides(this);
        transfer = new Transfer(this);
        intake = new Intake(this);

        time = new ElapsedTime();
        time.startTime();



        waitForStart();

        // DriveBase
        // ==========================================================================================================================================================================

        while (opModeIsActive())
        {
            // Drive Code
            centricDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, imu.getAngle(), gamepad1.right_trigger,
                    centricDrive.whichTurnMode(turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, 0.2, 0.2),
                            gamepad1.right_stick_x, gamepad1.back, time.seconds())
            );
//            drive.gamepadController(gamepad1);

            if (gamepad1.y)
            {
                imu.resetAngle();
            }


            // Algorithms
//            if (aprilTag.aprilTagDetected()) {telemetry.addData("Heading", aprilTag.getFieldYaw());}
//
//            if (aprilTag.aprilTagDetected())
//            {
//                if (gamepad1.a && (aprilTag.getDetectionID() == 16  || aprilTag.getDetectionID()== 13) && !gamepad1.start)
//                {
//                    autoBucket = new AutoBucket(new MecanumDrive(hardwareMap, new Pose2d(aprilTag.getFieldX(), aprilTag.getFieldY(), Math.toRadians(aprilTag.getFieldYaw()))), slide, elbow, wrist, claw);
//                    autoBucket.bucketRun(aprilTag.getFieldX(), aprilTag.getFieldY(), Math.toRadians((aprilTag.getFieldYaw())), aprilTag.getDetectionID());
//                }
//            }

            // Accessories
            //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
            vSlides.analogControl(1, gamepad2.right_stick_y, false, gamepad2.right_stick_button, true, vSlides.encoderBound, true);
            hSlides.analogControl(1, gamepad2.left_stick_y, false, gamepad2.left_stick_button, true, hSlides.encoderBound, true);

            transfer.driveServo(1, gamepad2.y);
            transfer.driveServo(0, gamepad2.a);

            intake.driveServoBoolean(gamepad2.right_trigger > 0.25, gamepad2.right_bumper);

        }

    }

}
