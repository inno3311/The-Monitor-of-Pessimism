package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.aprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.controller.MechanicalDriveBase;

@Autonomous(name = "AprilTagLoc")
public class AprilTagLoc extends LinearOpMode
{
    AprilTagMaster aprilTagMaster;

    @Override
    public void runOpMode() throws InterruptedException
    {
        aprilTagMaster = new AprilTagMaster(hardwareMap);

        waitForStart();

        while (opModeIsActive())
        {
            aprilTagMaster.tagsTelemetry(telemetry);
        }

    }
}
