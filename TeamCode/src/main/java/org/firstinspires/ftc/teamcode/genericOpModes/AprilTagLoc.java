package org.firstinspires.ftc.teamcode.opModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.aprilTags.AprilTagMaster;

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
