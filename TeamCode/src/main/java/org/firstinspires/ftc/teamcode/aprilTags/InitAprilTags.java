package org.firstinspires.ftc.teamcode.aprilTags;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controller.MecanumSynchronousDriver;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class InitAprilTags
{
    AprilTagProcessor aprilTagProcessor;
    AprilTagMaster aprilTagMaster;
    DriveToTag driveToTag;
    ElapsedTime elapsedTime;

    public void initAprilTags(MecanumSynchronousDriver driver, HardwareMap hardwareMap, Telemetry telemetry)
    {
        elapsedTime = new ElapsedTime();

        aprilTagMaster = new AprilTagMaster(driver, hardwareMap);

        driveToTag = new DriveToTag(hardwareMap, telemetry, elapsedTime, new ElapsedTime(), aprilTagMaster);
    }

    public AprilTagMaster getAprilTagMaster()
    {
        return aprilTagMaster;
    }

    public DriveToTag getDriveToTag()
    {
        return driveToTag;
    }
}
