package org.firstinspires.ftc.teamcode.RobotChildren;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.controller.DriveController;
import org.firstinspires.ftc.teamcode.fieldCentric.CentricDrive;
import org.firstinspires.ftc.teamcode.fieldCentric.TurnToHeading;
import org.firstinspires.ftc.teamcode.initialization.Initialization;

@TeleOp(name = "Prototype", group = "proto")
public class NessieTeleOp extends LinearOpMode
{
    private static final boolean USE_WEBCAM = false;  // true for webcam, false for phone camera
    // DriveBase
    DriveController driveController;
    TurnToHeading turnToHeading;
    CentricDrive centricDrive;
    IMUControl imu;

    // Accessories
    Slide slide;
    Elbow elbow;
    Hang hang;
    Wrist wrist;
    Claw claw;

    // Sensor
    TouchSensor slideLimit;
    TouchSensor elbowLimit;

    //Other
    ElapsedTime time;
    Initialization initialization;

    @Override
    public void runOpMode() throws InterruptedException
    {
        driveController = new DriveController(hardwareMap);
        imu = new IMUControl(hardwareMap, telemetry);
        turnToHeading = new TurnToHeading(telemetry, driveController, imu);
        centricDrive = new CentricDrive(driveController, telemetry);
        slide = new Slide(this);
        elbow = new Elbow(this);

        hang = new Hang(this);
        wrist = new Wrist(this);
        claw = new Claw(this);

        slideLimit = hardwareMap.get(TouchSensor.class, "slideLimit");
        elbowLimit = hardwareMap.get(TouchSensor.class, "elbowLimit");

        time = new ElapsedTime();
        time.startTime();

        initialization = new Initialization(slide, slideLimit, elbow, elbowLimit);
        initialization.initialization();

        waitForStart();

        while (opModeIsActive())
        {

            centricDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, imu.getAngle(), gamepad1.right_trigger,
                    centricDrive.whichTurnMode(turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, 0.2, 0.2),
                            gamepad1.right_stick_x, gamepad1.back, time.seconds())
            );
//            driveController.gamepadController(gamepad1);

            if (gamepad2.dpad_up)
            {
                slide.encoderPresets(Slide.Presets.TOP_CHAMBER);
                elbow.encoderPresets(Elbow.Presets.TOP_CHAMBER);
            }
            else if (gamepad2.dpad_down)
            {
                slide.encoderPresets(Slide.Presets.PICKUP_FLOOR);
                elbow.encoderPresets(Elbow.Presets.PICKUP_FLOOR);
            }
            else if (gamepad2.dpad_left)
            {
                slide.encoderPresets(Slide.Presets.TOP_BUCKET);
                elbow.encoderPresets(Elbow.Presets.TOP_BUCKET);
            }
            else if (gamepad2.dpad_right)
            {
                slide.encoderPresets(Slide.Presets.BOTTOM_BUCKET);


                elbow.encoderPresets(Elbow.Presets.BOTTOM_BUCKET);
            }
            else
            {
                slide.analogControl(1, gamepad2.left_stick_y, true,false, slideLimit.isPressed(), -2150, true);
                elbow.analogControl(1, gamepad2.right_stick_y, true, false, elbowLimit.isPressed(), false);
            }

            hang.simpleDrive(1, gamepad2.y, gamepad2.a);

            if (gamepad2.right_bumper)   //close
            {
                claw.driveServo(0);
            }
            else if (gamepad2.right_trigger > 0.2) //open
            {
                claw.driveServo(1);
            }

            if (gamepad2.left_bumper)
            {
                wrist.driveServo(1);
            }
            else if (gamepad2.left_trigger > 0.2)
            {
                wrist.driveServo(0.3);
            }

            slide.automaticEncoderReset(slideLimit.isPressed());
            elbow.automaticEncoderReset(elbowLimit.isPressed());

            slide.telemetry();
            elbow.telemetry();
            hang.telemetry();
            telemetry.update();
        }

    }

}
