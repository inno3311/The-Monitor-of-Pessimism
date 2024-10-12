package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.prototype.ProtoLinearSlide;
import org.firstinspires.ftc.teamcode.prototype.ProtoSlideTheta;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.TankDrive;
import org.firstinspires.ftc.teamcode.roadrunner.tuning.TuningOpModes;

@TeleOp(name="Push Yellow", group="Linear OpMode")
public final class PushYellows extends LinearOpMode
{

    ProtoSlideTheta protoSlideTheta;
    ProtoLinearSlide protoLinearSlide;

    @Override
    public void runOpMode() throws InterruptedException
    {
        Pose2d beginPose = new Pose2d(0, -55, Math.toRadians(90));
        protoLinearSlide = new ProtoLinearSlide(this);
        protoSlideTheta = new ProtoSlideTheta(this);
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class))
        {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
            int a = 0;
            int b = -35;
            int x = 47;
            int y = -53;
            waitForStart();

            TrajectoryActionBuilder yellow_run = drive.actionBuilder(beginPose)
                    .splineToSplineHeading(new Pose2d(-57, -55, Math.toRadians(225)), Math.toRadians(225)) //basket position
                    .setTangent(Math.toRadians(225)).setReversed(true)
                    .splineToSplineHeading(new Pose2d(-40, -36, Math.toRadians(270)), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-40, -14), Math.toRadians(90))// beginning of loop back
                    .splineToSplineHeading(new Pose2d(-52, -14, Math.toRadians(270)), Math.toRadians(180))
                    .splineToConstantHeading(new Vector2d(-54, -26), Math.toRadians(270)) //first sample pickup
                    .splineToSplineHeading(new Pose2d(-64, -46, Math.toRadians(225)), Math.toRadians(225)) //end of first lap

                    .setTangent(Math.toRadians(225)).setReversed(true)
                    .splineToSplineHeading(new Pose2d(-48, -36, Math.toRadians(270)), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-48, -14), Math.toRadians(90))// beginning of loop back
                    .splineToSplineHeading(new Pose2d(-60, -14, Math.toRadians(270)), Math.toRadians(180))
                    .splineToConstantHeading(new Vector2d(-64, -26), Math.toRadians(270)) //second sample pickup
                    .splineToSplineHeading(new Pose2d(-64, -45, Math.toRadians(225)), Math.toRadians(225)) //end of second lap

                    .setTangent(Math.toRadians(225)).setReversed(true)
                    .splineToSplineHeading(new Pose2d(-52, -36, Math.toRadians(270)), Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-52, -14), Math.toRadians(90))// beginning of loop back
                    .splineToSplineHeading(new Pose2d(-68, -14, Math.toRadians(270)), Math.toRadians(180))
                    .splineToConstantHeading(new Vector2d(-72, -22), Math.toRadians(270)) //third sample pickup
                    .splineToConstantHeading(new Vector2d(-72, -45), Math.toRadians(270)) //end of third lap

                    .setTangent(Math.toRadians(90))
                    .splineToConstantHeading(new Vector2d(-24, -6), Math.toRadians(0));


            TrajectoryActionBuilder red_run = drive.actionBuilder(beginPose)
                    .splineToConstantHeading(new Vector2d(a, b), Math.toRadians(90))
                    .waitSeconds(1) //space for program to hook specimen on bar
                    .splineToSplineHeading(new Pose2d(30, -40, Math.toRadians(270)), Math.toRadians(360))
                    .splineToSplineHeading(new Pose2d(42, -10, Math.toRadians(270)), Math.toRadians(360))
                    .splineToConstantHeading(new Vector2d(46, -20), Math.toRadians(270))
                    .splineToConstantHeading(new Vector2d(x, y), Math.toRadians(270))
                    .waitSeconds(1) //space for program that obtains specimen
                    .splineToConstantHeading(new Vector2d(x, y+3), Math.toRadians(90))
                    .splineToSplineHeading(new Pose2d(a+1, b, Math.toRadians(90)), Math.toRadians(90)) //end of first lap

                    .waitSeconds(1) //space for program to hook specimen on bar
                    .splineToSplineHeading(new Pose2d(30, -40, Math.toRadians(270)), Math.toRadians(360))
                    .splineToSplineHeading(new Pose2d(54, -10, Math.toRadians(270)), Math.toRadians(360))
                    .splineToConstantHeading(new Vector2d(56, -20), Math.toRadians(270))
                    .splineToConstantHeading(new Vector2d(x, y), Math.toRadians(270))
                    .waitSeconds(1) //space for program that obtains specimen
                    .splineToConstantHeading(new Vector2d(x, y+3), Math.toRadians(90))
                    .splineToSplineHeading(new Pose2d(a+2, b, Math.toRadians(90)), Math.toRadians(90)) //end of second lap

                    .waitSeconds(1) //space for program to hook specimen on bar
                    .splineToSplineHeading(new Pose2d(30, -40, Math.toRadians(270)), Math.toRadians(360))
                    .splineToSplineHeading(new Pose2d(58, -10, Math.toRadians(270)), Math.toRadians(360))
                    .splineToConstantHeading(new Vector2d(61, -20), Math.toRadians(270))
                    .splineToConstantHeading(new Vector2d(61, y), Math.toRadians(270)) //this line may not be necessary if robot can catch the sample on its side
                    .splineToConstantHeading(new Vector2d(x,y), Math.toRadians(180))
                    .waitSeconds(1) //space for program that obtains specimen
                    .splineToConstantHeading(new Vector2d(x, y+3), Math.toRadians(90))
                    .splineToSplineHeading(new Pose2d(a+2, b, Math.toRadians(90)), Math.toRadians(90)) //end of second lap
                    .waitSeconds(1) //space for program to hook specimen on bar
                    .splineToConstantHeading(new Vector2d(53, -58), Math.toRadians(270)); //parking
//
            TrajectoryActionBuilder trajectoryActionBuilder = drive.actionBuilder(beginPose)
                .waitSeconds(2);


            Action action = trajectoryActionBuilder
                .build();

            Actions.runBlocking(new SequentialAction(protoSlideTheta.action(-1250, 1), protoLinearSlide.action(-1090, 0.25), action));
//


        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class)) {
            TankDrive drive = new TankDrive(hardwareMap, beginPose);

            waitForStart();

            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .splineTo(new Vector2d(30, 30), Math.PI / 2)
                            .splineTo(new Vector2d(0, 60), Math.PI)
                            .build());
        } else {
            throw new RuntimeException();
        }
    }
}
