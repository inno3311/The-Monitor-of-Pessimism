package org.firstinspires.ftc.teamcode.genericOpModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.IMU.IMUControl;
import org.firstinspires.ftc.teamcode.controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.fieldCentric.CentricDriveTheBetterVersion;
import org.firstinspires.ftc.teamcode.fieldCentric.TurnToHeading;

@TeleOp(name = "Centric Command", group = "FieldCentric")
public class CentricCommandBetterVersion extends OpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    TurnToHeading turnToHeading;
    CentricDriveTheBetterVersion centricDriveTheBetterVersion;
    IMUControl imu;

    @Override
    public void init()
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        imu = new IMUControl(hardwareMap, telemetry);
        turnToHeading = new TurnToHeading(telemetry, mechanicalDriveBase, imu);
        centricDriveTheBetterVersion = new CentricDriveTheBetterVersion(mechanicalDriveBase, telemetry, new ElapsedTime());
    }

    @Override
    public void loop()
    {
        centricDriveTheBetterVersion.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, imu.getAngle(), turnToHeading.turnToHeading(gamepad1.right_stick_x, gamepad1.right_stick_y, 0.2, 0.2), new Gamepad());

    }
}
