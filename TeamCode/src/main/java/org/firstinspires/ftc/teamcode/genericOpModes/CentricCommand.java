package org.firstinspires.ftc.teamcode.genericOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.controller.DriveController;
import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.fieldCentric.CentricDrive;
import org.firstinspires.ftc.teamcode.fieldCentric.TurnToHeading;
import org.firstinspires.ftc.teamcode.util.PIDController;

@TeleOp(name = "Centric Command *Don't run this one*", group = "FieldCentric")
public class CentricCommand extends OpMode
{
    DriveController driveController;
    TurnToHeading turnToHeading;
    CentricDrive centricDrive;
    IMUControl imu;

    PIDController pid;

    @Override
    public void init()
    {
        driveController = new DriveController(hardwareMap);
        imu = new IMUControl(hardwareMap, telemetry);
        turnToHeading = new TurnToHeading(telemetry, driveController, imu);
        centricDrive = new CentricDrive(driveController, telemetry);
    }

    @Override
    public void loop()
    {
        centricDrive.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, imu.getAngle(), turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, 0.2, 0.2),  gamepad1.right_trigger);
    }
}
