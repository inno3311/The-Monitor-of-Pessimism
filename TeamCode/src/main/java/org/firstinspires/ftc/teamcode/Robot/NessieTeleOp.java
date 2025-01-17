package org.firstinspires.ftc.teamcode.Robot;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.algirithums.AutoBucket;
import org.firstinspires.ftc.teamcode.algirithums.AutoHang;
import org.firstinspires.ftc.teamcode.algirithums.samplePickup.AutoPickup;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.algirithums.MotorTicksConversion;
import org.firstinspires.ftc.teamcode.aprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.fieldCentric.CentricDrive;
import org.firstinspires.ftc.teamcode.fieldCentric.TurnToHeading;
import org.firstinspires.ftc.teamcode.initialization.Initialization;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.vision.SampleSeeker;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.io.File;

@TeleOp(name = "TeleOp", group = "proto")
public class NessieTeleOp extends LinearOpMode
{
    // Sensors
    AprilTagMaster aprilTag;
    IMUControl imu;
    TouchSensor slideLimit;
    TouchSensor elbowLimit;

    // Algorithms
    SampleSeeker seeker;
    AutoPickup autoPickup;
    AutoBucket autoBucket;
    AutoHang autoHang;
    Initialization initialization;

    // DriveBase
    MecanumDrive drive;
    TurnToHeading turnToHeading;
    CentricDrive centricDrive;

    // Accessories
    Slide slide;
    Elbow elbow;
    Hang hang;
    Wrist wrist;
    Claw claw;

    //Other
    ElapsedTime time;
    MotorTicksConversion ticksConversion;
    private double hangFlag = 0;


    @Override
    public void runOpMode() throws InterruptedException
    {
        aprilTag = new AprilTagMaster(hardwareMap);
        imu = new IMUControl(hardwareMap, telemetry);
        slideLimit = hardwareMap.get(TouchSensor.class, "slideLimit");
        elbowLimit = hardwareMap.get(TouchSensor.class, "elbowLimit");

        drive = new MecanumDrive(hardwareMap, null);
        turnToHeading = new TurnToHeading(telemetry, drive, imu);
        centricDrive = new CentricDrive(drive, telemetry);

        slide = new Slide(this);
        elbow = new Elbow(this);
        hang = new Hang(this);
        wrist = new Wrist(this);
        claw = new Claw(this);

        time = new ElapsedTime();
        time.startTime();

        seeker = new SampleSeeker(telemetry);
        autoPickup = new AutoPickup(drive);
        autoHang = new AutoHang(drive, slide, elbow, hang, wrist, claw);

        ticksConversion = new MotorTicksConversion();

        initCamera();

        if (new File("/sdcard/FIRST/blocks/sounds/second.wav").exists())
        {
            SoundPlayer.getInstance().startPlaying(hardwareMap.appContext, new File("/sdcard/FIRST/blocks/sounds/second.wav"));
        }

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
            if (aprilTag.aprilTagDetected()) {telemetry.addData("Heading", aprilTag.getFieldYaw());}

            if (aprilTag.aprilTagDetected())
            {
                if (gamepad1.a && (aprilTag.getDetectionID() == 16  || aprilTag.getDetectionID()== 13) && !gamepad1.start)
                {
                    autoBucket = new AutoBucket(new MecanumDrive(hardwareMap, new Pose2d(aprilTag.getFieldX(), aprilTag.getFieldY(), Math.toRadians(aprilTag.getFieldYaw()))), slide, elbow, wrist, claw);
                    autoBucket.bucketRun(aprilTag.getFieldX(), aprilTag.getFieldY(), Math.toRadians((aprilTag.getFieldYaw())), aprilTag.getDetectionID());
                }
            }

            telemetry.addData("Object detected?", seeker.isObject_detected());

            if (seeker.isObject_detected())
            {
                double x_offset = 0;
                double y_offset = 3;
                double object_x_distance = calculate_x_distance(seeker.getAngle_x(), seeker.getCamera_height(), x_offset);
                double object_y_distance = calculate_y_distance(seeker.getAngle_y(), seeker.getCamera_height(), y_offset);
                telemetry.addData("angle x", seeker.getAngle_x());
                telemetry.addData("angle y", seeker.getAngle_y());
                telemetry.addData("object x distance", object_x_distance);
                telemetry.addData("object y distance", object_y_distance);
                if (gamepad1.b && !gamepad1.start)
                    {
                            autoPickup = new AutoPickup(new MecanumDrive(hardwareMap,new Pose2d(0, 0, Math.toRadians(90))));
//                        autoPickup.align(seeker.getDistance_x(), seeker.getDistance_y());
                            autoPickup.align(object_x_distance, object_y_distance);
                    }
            }
//            if (seeker.isObject_detected())
//            {
//                telemetry.addData("object x", seeker.getDistance_x());
//                telemetry.addData("object y", seeker.getDistance_y());
//                if (gamepad1.b && !gamepad1.start)
//                {
//                    centricDrive.drive(0, 0, 0, 0, autoPickup.align_angle(seeker.getDistance_x(), ticksConversion.linearSlideInCM()*slide.getMotorPosition()));
//                }
//            }

            // Slide and Elbow
            // ==========================================================================================================================================================================

            boolean runSlide = false;
            if (Math.abs(elbow.getMotorPosition() / ticksConversion.elbowInDegrees()) < 50 && slide.getMotorPosition() <= -1050) // The Greater then half is where the limit will kick in
            {
                runSlide = true;
                slide.encoderControl(-1000, 0.5);
            }

            if (Math.abs(elbow.getMotorPosition() / ticksConversion.elbowInDegrees()) > 92 && slide.getMotorPosition() <= -10)// The Greater then half is where the limit will kick in
            {
                runSlide = true;
                slide.encoderControl(0, 0.5);
            }

            if (hangFlag > time.seconds()) {}
            else if (gamepad2.dpad_up)
            {
                slide.encoderPresets(Slide.Presets.TOP_CHAMBER);
                elbow.encoderPresets(Elbow.Presets.TOP_CHAMBER);
            }
            else if (gamepad2.dpad_down)
            {
                slide.encoderPresets(Slide.Presets.PICKUP_WALL);
                elbow.encoderPresets(Elbow.Presets.PICKUP_WALL);
                wrist.driveServo(0.7);

            }
            else if (gamepad2.dpad_left)
            {
                slide.encoderPresets(Slide.Presets.TOP_BUCKET);
                elbow.encoderPresets(Elbow.Presets.TOP_BUCKET);
            }
            else if (gamepad2.dpad_right)
            {

            }
            else if (runSlide)
            {
                elbow.analogControl(1, gamepad2.right_stick_y, false, false, elbowLimit.isPressed(), -3300, true);
            }
            else
            {
                slide.analogControl(1, gamepad2.left_stick_y, true, gamepad2.left_stick_button, slideLimit.isPressed(), -2175, true);
                elbow.analogControl(1, gamepad2.right_stick_y, false, gamepad2.right_stick_button, elbowLimit.isPressed(), -3300, true);
            }

            // Hanging
            // ==========================================================================================================================================================================
            if (gamepad1.left_bumper || gamepad1.left_trigger > 0.25)
            {
                hangFlag = time.seconds() + 3;

            }


            //dpad up to extend hang
            //dpad down to retract hang
            if (hangFlag < time.seconds())
            {
                if (Math.abs(hang.getMotorPosition()) < 6600)
                {
                    hang.simpleDrive(1, gamepad1.dpad_down, gamepad1.dpad_up);
                }
            }

            if (gamepad1.left_bumper)
            {
                autoHang.prepare();
            }
            else if (gamepad1.left_trigger > 0.25)
            {
                autoHang.hang();
            }



            // Claw and Wrist
            // ==========================================================================================================================================================================

            if (gamepad2.right_bumper) //close
            {
                claw.driveServo(0);
            }
            else if (gamepad2.b && !gamepad2.start) //Half open
            {
                claw.driveServo(0.5);
            }
            else if (gamepad2.right_trigger > 0.2) //open
            {
                claw.driveServo(1);
            }

            if (gamepad2.left_bumper) //Back
            {
                if (elbow.getMotorPosition() > -900)
                {
                    wrist.driveServo(0.7);
                }
                else
                {
                    wrist.driveServo(1);
                }
            }
            else if (gamepad2.left_trigger > 0.2) // Up
            {
                wrist.driveServo(0);
            }
            
            slide.automaticEncoderReset(slideLimit.isPressed());
            elbow.automaticEncoderReset(elbowLimit.isPressed());


            // telemetry
            slide.telemetry();
            elbow.telemetry();
            hang.telemetry();
//            telemetry.update();
        }

    }
    private void initCamera()
    {
        //https://github.com/OpenFTC/EasyOpenCV/blob/master/doc/user_docs/camera_initialization_overview.md
        String camera_name = "Webcam 2";
        //OpenCvCamera camera = OpenCvCameraFactory.getInstance().createInternalCamera2(OpenCvInternalCamera2.CameraDirection.BACK);
        WebcamName webcamName = hardwareMap.get(WebcamName.class, camera_name);
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName);
        seeker = new SampleSeeker(telemetry);
        FtcDashboard.getInstance().startCameraStream(camera,0);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.startStreaming(320, 180, OpenCvCameraRotation.UPRIGHT);
                camera.setPipeline(seeker);
            }
            @Override
            public void onError(int errorCode)
            {
                telemetry.addData("Camera Failed","");
            }
        });
    }

    private double calculate_camera_height(double arm_angle, double arm_length, double camera_length_offset, double height_offset)
    {
        double height = (Math.sin(Math.toRadians(arm_angle)) * (arm_length + camera_length_offset)) + height_offset;
        return(height);
    }

    private double calculate_x_distance(double x_angle, double camera_height, double camera_x_offset)
    {
        double distance_x = (Math.tan(x_angle)*camera_height) + camera_x_offset;
        return(distance_x);
    }

    private double calculate_y_distance(double y_angle, double camera_height, double camera_y_offset)
    {
        double distance_y = (Math.tan(y_angle)*camera_height) + camera_y_offset;
        return(distance_y);
    }


    private double calculate_x_speed(double x_angle)
    {
        double x_speed = (Math.tan(x_angle));
        return(x_speed);
    }

    private double calculate_y_speed(double y_angle)
    {
        double y_speed = (Math.tan(y_angle));
        return(y_speed);
    }

}
