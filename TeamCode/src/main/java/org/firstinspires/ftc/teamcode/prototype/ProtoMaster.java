package org.firstinspires.ftc.teamcode.prototype;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
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
    ProtoClawWrist clawWrist;
    ProtoClawLeft clawLeft;
    ProtoClawRight clawRight;

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
        clawLeft = new ProtoClawLeft(this);
        clawRight = new ProtoClawRight(this);

        waitForStart();

        while (opModeIsActive())
        {


            //        centricDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, imu.getAngle(), turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, 0.2, 0.2));

            mechanicalDriveBase.gamepadController(gamepad1);

            linearSlide.analogControl(0.5, gamepad2.left_stick_y, false);

            slideTheta.analogControl(1, gamepad2.right_stick_y, false);

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
            else if (gamepad2.left_trigger > 0.2)
            {
                clawWrist.driveServo(0.5);
            }


            linearSlide.telemetry();
            slideTheta.telemetry();
            telemetry.update();
        }

    }

}
