package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
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
    private double target_X;
    private double target_Y;
    private double target_heading;

    public AutoBucket(MecanumDrive drive, Slide slide, Elbow elbow, Wrist wrist, Claw claw)
    {
        this.drive = drive;
        this.slide = slide;
        this.elbow = elbow;
        this.wrist = wrist;
        this.claw = claw;
    }

    public void bucketRun(double x, double y, double heading)
    {
        target_X = -49;
        target_Y = -56;
        target_heading = 225;

        TrajectoryActionBuilder bucket = drive.actionBuilder(new Pose2d(x,y, heading))
                //.waitSeconds(1)
                .afterTime(0, wrist.action(1))
                .afterTime(0, elbow.action(-2500, 1))
                .afterTime(0, slide.action(0, 0.5))
                .waitSeconds(1)
                .turnTo(Math.toRadians(target_heading))
                .waitSeconds(1)
                .strafeTo(new Vector2d(target_X,target_Y))
                .afterTime(0, slide.action(-2175, 1))
                .waitSeconds(0.5)
                .afterTime(0, elbow.action(-2400, 1))
                .waitSeconds(0.5)
                .afterTime(0, wrist.action(0))
                .waitSeconds(0.5);


        Action bucketAction = bucket.build();

        Actions.runBlocking(bucketAction);

    }

}
