package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.TurnConstraints;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import org.firstinspires.ftc.teamcode.RobotChildren.Slide;
import org.firstinspires.ftc.teamcode.RobotChildren.Elbow;
import org.firstinspires.ftc.teamcode.RobotChildren.Wrist;
import org.firstinspires.ftc.teamcode.RobotChildren.Claw;
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

    public void bucketRun(double x, double y, double heading, int tag)
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
            targetWaypoint_X = -45;
            targetWaypoint_Y = -45;
            target_heading = Math.toRadians(225);
        }
        else
        {
            target_X = 60;
            target_Y = 60;
            targetWaypoint_X = 45;
            targetWaypoint_Y = 45;
            target_heading = Math.toRadians(45);
        }

        TrajectoryActionBuilder bucket = drive.actionBuilder(new Pose2d(x,y, heading))
                //.waitSeconds(1)
                .afterTime(0, wrist.action(1))
                .afterTime(0, elbow.action(-2500, 1))
                .afterTime(0, slide.action(0, 0.5))
//                .strafeTo(new Vector2d(targetWaypoint_X, targetWaypoint_Y),new TranslationalVelConstraint(100))
//               .splineToConstantHeading(new Vector2d(targetWaypoint_X, targetWaypoint_Y), target_heading - 45, new TranslationalVelConstraint(100))
                .splineToSplineHeading(new Pose2d(targetWaypoint_X, targetWaypoint_Y, target_heading), target_heading, new TranslationalVelConstraint(50))
                .waitSeconds(0.25)
                .splineToConstantHeading(new Vector2d(target_X,target_Y), target_heading)
                .afterTime(0, slide.action(-2175, 1))
                .afterTime(0, elbow.action(-2400, 1))
                .waitSeconds(0.5)
                .afterTime(0, wrist.action(0))
                .waitSeconds(0.1);


        Action bucketAction = bucket.build();

        Actions.runBlocking(bucketAction);

    }

}
