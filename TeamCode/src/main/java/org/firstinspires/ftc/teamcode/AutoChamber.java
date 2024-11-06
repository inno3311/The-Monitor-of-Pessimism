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

public class AutoChamber
{

    MecanumDrive drive;
    Slide slide;
    Elbow elbow;
    Claw claw;

    public AutoChamber(MecanumDrive drive, Slide slide, Elbow elbow, Claw claw)
    {
        this.drive = drive;
        this.slide = slide;
        this.elbow = elbow;
        this.claw = claw;
    }

    public void bucketRun(double x, double y, double heading)
    {
        TrajectoryActionBuilder bucket = drive.actionBuilder(new Pose2d(x,y,heading)).splineToSplineHeading(new Pose2d(0,-29, Math.toRadians(90)), Math.toRadians(90));

        Action bucketAction = bucket.build();

        Actions.runBlocking(new SequentialAction(elbow.action(-1300, 1), slide.action(-110, 0.5), bucketAction));
        claw.driveServo(0);
    }

}
