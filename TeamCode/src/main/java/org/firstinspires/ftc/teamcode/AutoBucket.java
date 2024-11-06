package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import org.firstinspires.ftc.teamcode.prototype.Claw;
import org.firstinspires.ftc.teamcode.prototype.Elbow;
import org.firstinspires.ftc.teamcode.prototype.Slide;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class AutoBucket
{

    MecanumDrive drive;
    Slide slide;
    Elbow elbow;
    Claw claw;

    public AutoBucket(MecanumDrive drive, Slide slide, Elbow elbow, Claw claw)
    {
        this.drive = drive;
        this.slide = slide;
        this.elbow = elbow;
        this.claw = claw;
    }

    public void bucketRun(double x, double y, double heading)
    {
        TrajectoryActionBuilder bucket = drive.actionBuilder(new Pose2d(x,y,heading)).splineToSplineHeading(new Pose2d(-55,-55, Math.toRadians(225)), Math.toRadians(225));

        Action bucketAction = bucket.build();

        Actions.runBlocking(new SequentialAction(elbow.action(-2050, 1), slide.action(-2150, 0.5), bucketAction));
        claw.driveServo(0);
    }

}
