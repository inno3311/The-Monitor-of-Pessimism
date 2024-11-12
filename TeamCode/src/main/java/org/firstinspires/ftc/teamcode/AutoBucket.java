package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
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

        TrajectoryActionBuilder waitTrajectory = drive.actionBuilder(new Pose2d(x,y,heading))
                .waitSeconds(2);

        TrajectoryActionBuilder mezTrajectory = drive.actionBuilder(new Pose2d(x,y, heading))
                //.waitSeconds(1)
                .afterTime(0,elbow.action(-2100, 1))
                .afterTime(0,slide.action(-2175, 0.5))
                .waitSeconds(3)
                .turnTo(Math.toRadians(225))
                .waitSeconds(1)
                .strafeTo(new Vector2d(-48,-58))
                //.splineToSplineHeading(new Pose2d(-55,-55, Math.toRadians(225)), Math.toRadians(225))
                .afterTime(0,claw.action(0))
                .waitSeconds(1);

        Action Mez = mezTrajectory.build();


        //Action wait = waitTrajectory.build();

        //Action bucketAction = bucket.build();

        //Actions.runBlocking(new SequentialAction(elbow.action(-2050, 1), wait, slide.action(-2150, 0.5), wait, bucketAction));

        Actions.runBlocking(Mez);

        //claw.driveServo(0);
    }

}
