package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.ftc.Actions;
import org.firstinspires.ftc.teamcode.RobotChildren.Slide;
import org.firstinspires.ftc.teamcode.RobotChildren.Elbow;
import org.firstinspires.ftc.teamcode.RobotChildren.Claw;
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
        TrajectoryActionBuilder bucket = drive.actionBuilder(new Pose2d(x,y,heading))
                .afterTime(0,elbow.action(-1300, 1))
                .afterTime(0,slide.action(-110, 0.5))
                .waitSeconds(2)
                .splineToSplineHeading(new Pose2d(0,-29, Math.toRadians(90)), Math.toRadians(90));

        Action bucketAction = bucket.build();

        Actions.runBlocking(bucketAction);

        claw.driveServo(0);
    }

}
