package org.firstinspires.ftc.teamcode.roadrunner.tuning;

import com.acmerobotics.dashboard.canvas.Spline;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Trajectory;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.prototype.ProtoSlideTheta;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.TankDrive;

public final class SplineTest extends LinearOpMode
{
    ProtoSlideTheta protoSlideTheta;

    @Override
    public void runOpMode() throws InterruptedException
    {
        protoSlideTheta = new ProtoSlideTheta(this);
        Pose2d beginPose = new Pose2d(0, 45, Math.toRadians(90));
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class))
        {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            TrajectoryActionBuilder trajectory1 = drive.actionBuilder(beginPose).lineToY(50);

            Action act = trajectory1.build();

            waitForStart();

            Actions.runBlocking(new SequentialAction(
                    act,
                    protoSlideTheta.action(50, 0.5)
            ));

//            Actions.runBlocking(
//                  drive.actionBuilder(beginPose)
//                        .lineToY(-34)
//                        .lineToY(-40)
//                        .strafeTo(new Vector2d(-54, -40))
//                        .setTangent(Math.toRadians(90))
//                        .lineToYSplineHeading(-10, Math.toRadians(0))
//                        .setTangent(Math.toRadians(0))
//                        .lineToXSplineHeading(20, Math.toRadians(0))
//                        .splineToConstantHeading(new Vector2d(50,-34),0)
//                        .waitSeconds(2)
//                        .setReversed(true)
//                        .splineToConstantHeading(new Vector2d(20,-10),Math.toRadians(180))
//                        .setTangent(Math.toRadians(180))
//                        .lineToX(-60)
//                        .waitSeconds(2)
//                        .setTangent(Math.toRadians(0))
//                        .lineToXSplineHeading(20, Math.toRadians(0))
//                        .splineToConstantHeading(new Vector2d(50,-34),0)
//                        .waitSeconds(2)
//                        .build());

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
