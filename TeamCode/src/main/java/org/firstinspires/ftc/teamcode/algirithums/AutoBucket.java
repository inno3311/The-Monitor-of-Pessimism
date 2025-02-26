package org.firstinspires.ftc.teamcode.algirithums;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import org.firstinspires.ftc.teamcode.Robot.Slide;
import org.firstinspires.ftc.teamcode.Robot.Elbow;
import org.firstinspires.ftc.teamcode.Robot.Wrist;
import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class AutoBucket
{
    MecanumDrive drive;
    Slide slide;
    Elbow elbow;
    Wrist wrist;
    Claw claw;

    public AutoBucket(MecanumDrive drive, Slide slide, Elbow elbow, Wrist wrist, Claw claw)
    {
        this.drive = drive;
        this.slide = slide;
        this.elbow = elbow;
        this.wrist = wrist;
        this.claw = claw;
    }

    public Action bucketRun(double x, double y, double heading, int tag)
    {
        double target_X;
        double target_Y;
        double targetWaypoint_X;
        double targetWaypoint_Y;
        double target_heading;

        if (tag == 16 || tag == 15 || tag == 14)
        {
            target_X = -60;
            target_Y = -60;
            targetWaypoint_X = -46;
            targetWaypoint_Y = -50;
            target_heading = Math.toRadians(225);
        }
        else
        {
            target_X = 60;
            target_Y = 60;
            targetWaypoint_X = 48;
            targetWaypoint_Y = 48;
            target_heading = Math.toRadians(45);
        }

        TrajectoryActionBuilder bucket = drive.actionBuilder(new Pose2d(x,y, heading))

            .afterTime(0, wrist.action(1))
            .afterTime(0, elbow.action(-2450, 1))
            .afterTime(1, slide.action(-2150, 1))
            .afterTime(1, wrist.action(0))
            .strafeToLinearHeading(new Vector2d(targetWaypoint_X, targetWaypoint_Y), target_heading)

            .waitSeconds(.2)
            .strafeToLinearHeading(new Vector2d(target_X, target_Y), target_heading);



        Action bucketAction = bucket.build();

        //Actions.runBlocking(bucketAction);

        return bucketAction;

    }

    public Action wall_pickup(double x, double y, double heading, int tag)
    {
        double target_X;
        double target_Y;
        double targetWaypoint_X;
        double targetWaypoint_Y;
        double target_heading;

        if (tag == 16 || tag == 15 || tag == 14)
        {
            target_X = 45;
            target_Y = -65;
            targetWaypoint_X = 45;
            targetWaypoint_Y = -45;
            target_heading = Math.toRadians(270);
        }
        else
        {
            target_X = -60;
            target_Y = 65;
            targetWaypoint_X = -45;
            targetWaypoint_Y = 45;
            target_heading = Math.toRadians(90);
        }

        TrajectoryActionBuilder wall_pickup = drive.actionBuilder(new Pose2d(x,y, heading))

                .afterTime(0, wrist.action(0.7))
                .afterTime(0, claw.action(0.6))
                .afterTime(0, elbow.action(-210, 1))
                .afterTime(1, slide.action(0,1))
                .strafeToLinearHeading(new Vector2d(targetWaypoint_X, targetWaypoint_Y), target_heading, new TranslationalVelConstraint(70))
                .waitSeconds(.2)
                .strafeToLinearHeading(new Vector2d(target_X, target_Y), target_heading, new TranslationalVelConstraint(15))
                .afterTime(0, claw.action(0))
                .waitSeconds(0.3)
                .afterTime(0, elbow.action(-1350,1))
                ;



        Action wallPickup = wall_pickup.build();

        //Actions.runBlocking(bucketAction);

        return wallPickup;

    }


}
