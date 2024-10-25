package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.prototype.ProtoClaw;
import org.firstinspires.ftc.teamcode.prototype.ProtoWrist;
import org.firstinspires.ftc.teamcode.prototype.ProtoLinearSlide;
import org.firstinspires.ftc.teamcode.prototype.ProtoSlideTheta;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.tuning.TuningOpModes;

@Autonomous(name="SpecimenRun2", group="Linear OpMode")
public final class HighChamberSpecimens extends LinearOpMode {



    ProtoSlideTheta protoSlideTheta;
    ProtoLinearSlide protoLinearSlide;

    ProtoWrist clawWrist;
    //ProtoClawLeft clawLeft;
    ProtoClaw claw;


    @Override
    public void runOpMode() throws InterruptedException {

        protoLinearSlide = new ProtoLinearSlide(this);
        protoSlideTheta = new ProtoSlideTheta(this);
        clawWrist = new ProtoWrist(this);
        //clawLeft = new ProtoClawLeft(this);
        claw = new ProtoClaw(this);

        Pose2d beginPose = new Pose2d(0, -55, Math.toRadians(90));
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            waitForStart();

            TrajectoryActionBuilder trajectoryActionBuilderWait = drive.actionBuilder(beginPose)
                .waitSeconds(1)

                //.waitSeconds(10)

                ;

            TrajectoryActionBuilder trajectoryActionBuilder = drive.actionBuilder(beginPose)
                .waitSeconds(5)
                .splineToConstantHeading(new Vector2d(0, -32), Math.toRadians(90))
                //.waitSeconds(10)

                ;

            TrajectoryActionBuilder trajectoryActionBuilderEnd = drive.actionBuilder(beginPose)
                .setTangent(Math.toRadians(270))
                .splineToSplineHeading(new Pose2d(50, -40, Math.toRadians(90)), Math.toRadians(360))
//                .setTangent(Math.toRadians(270)).setReversed(true)
//                .splineToConstantHeading(new Vector2d(0, -40), Math.toRadians(270))
//
//                .splineToConstantHeading(new Vector2d(50, -50), Math.toRadians(270))
                .waitSeconds(10);

            TrajectoryActionBuilder trajectoryActionBuilderMezTest = drive.actionBuilder(beginPose)
                .strafeTo(new Vector2d(0,-30))  //drive to chamber
                .strafeTo(new Vector2d(0,-45))  //back up from chamber
                .strafeToLinearHeading(new Vector2d(24, -34), Math.toRadians(0))  // start drive to samples
                .splineToConstantHeading(new Vector2d(40,-24),Math.toRadians(0))
                .setTangent(0)
                .turnTo(Math.toRadians(270))
                .strafeTo(new Vector2d(48,-60));



            Action actionTest = trajectoryActionBuilderMezTest
                .build();

            Action actionDriveToBar = trajectoryActionBuilder
                .build();

            Action actionEnd = trajectoryActionBuilderEnd
                .build();

            Action actionWait = trajectoryActionBuilderWait
                .build();

//            Actions.runBlocking(actionTest
////                new SequentialAction(
////                    //clawLeft.action(0),
////                    claw.action(0),
////                    actionWait,
////                    actionWait,
////                    claw.action(1),
////                    actionWait,
////                    actionWait,
////                    claw.action(0),
////                    actionWait,
////                    actionWait
//
////                    protoSlideTheta.action( -1000, 0.4),
////                    protoLinearSlide.action(-1300, 0.3),
////                    new ParallelAction(actionDriveToBar,claw.action(0)),
////                    protoSlideTheta.action ( 0, 0.4),
////                    protoLinearSlide.action(0, 0.3),
////                    actionEnd
////                )
//            );


            //Mesloh attempt to use afterDisp to hang a specs.
            TrajectoryActionBuilder trajectoryActionBuilderMez2= drive.actionBuilder(beginPose)

                .afterTime(0,claw.action(0))
                .afterTime(0, protoSlideTheta.action( -1165, 0.5))
                .afterTime(0, protoLinearSlide.action(-1000, 0.5))
                .waitSeconds(3)
                .splineToConstantHeading(new Vector2d(0, -32), Math.toRadians(90))
                .waitSeconds(1)
                .splineToConstantHeading(new Vector2d(0, -50), Math.toRadians(90))
                .afterTime(0, protoSlideTheta.action( -800, 1))
                .setReversed(true)
                .afterTime(0,claw.action(1))
                .afterTime(0, protoSlideTheta.action( 0, 0.5))
                .afterTime(0, protoLinearSlide.action(0, 0.5))
                .splineToConstantHeading(new Vector2d(40, -50), Math.toRadians(90))
                .waitSeconds(3);
//                .strafeTo(new Vector2d(0,-30))  //drive to chamber
//                .strafeTo(new Vector2d(0,-45))  //back up from chamber
//                .strafeToLinearHeading(new Vector2d(24, -34), Math.toRadians(0))  // start drive to samples
//                .splineToConstantHeading(new Vector2d(40,-24),Math.toRadians(0))
//                .setTangent(0)
//                .turnTo(Math.toRadians(270))
//                .strafeTo(new Vector2d(48,-60));

            Action actionMez2 = trajectoryActionBuilderMez2
                .build();

            Actions.runBlocking(actionMez2);

//            Actions.runBlocking(
//                new SequentialAction(claw.action(0),
//                    protoSlideTheta.action( -1165, 0.5),
//                    protoLinearSlide.action(-920, 0.5),
//                    new SequentialAction(
//                        new ParallelAction(claw.action(0),
//                            claw.action(0),actionDriveToBar),
//                            protoSlideTheta.action( -950, 0.4),
////                            protoLinearSlide.action(0, 0.3)),
//                    actionWait,protoSlideTheta.action( -800, 0.5),
//                        actionWait,claw.action(1)
//                )
//            ));


        }  else {
            throw new RuntimeException();
        }
    }
}
