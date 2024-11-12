package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.RobotChildren.Claw;
import org.firstinspires.ftc.teamcode.RobotChildren.Elbow;
import org.firstinspires.ftc.teamcode.RobotChildren.Wrist;
import org.firstinspires.ftc.teamcode.RobotChildren.Slide;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.tuning.TuningOpModes;

@Autonomous(name="SpecimenRun2", group="Linear OpMode")
public final class HighChamberSpecimens extends LinearOpMode {



    Elbow elbow;
    Slide slide;

    Wrist wrist;
    //ProtoClawLeft clawLeft;
    Claw claw;


    @Override
    public void runOpMode() throws InterruptedException {

        slide = new Slide(this);
        elbow = new Elbow(this);
        wrist = new Wrist(this);
        //clawLeft = new ProtoClawLeft(this);
        claw = new Claw(this);

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
////                    elbow.action( -1000, 0.4),
////                    slide.action(-1300, 0.3),
////                    new ParallelAction(actionDriveToBar,claw.action(0)),
////                    elbow.action ( 0, 0.4),
////                    slide.action(0, 0.3),
////                    actionEnd
////                )
//            );

                int x = 44;
                int y = -48;
            //Mesloh attempt to use afterDisp to hang a specs.
            TrajectoryActionBuilder trajectoryActionBuilderMez2= drive.actionBuilder(beginPose)

                .afterTime(0,claw.action(1)) //close claw
                .afterTime(0, elbow.action( -1165, 0.5))
                .afterTime(0, slide.action(-1100, 0.5))
                .waitSeconds(1)
                .splineToConstantHeading(new Vector2d( 0,-29), Math.toRadians(90)) //move to chamber
                .afterTime(0, claw.action(0)) //begins when the action after it begins
                .waitSeconds(.1)
                .setTangent(Math.toRadians(360))
                .splineToSplineHeading(new Pose2d(19, -30, Math.toRadians(180)), Math.toRadians(360))
                .afterTime(0, elbow.action( 0, 0.5))
                .afterTime(0, slide.action(0, 0.5))
                //.waitSeconds(.1)
                .splineToSplineHeading(new Pose2d(36, -30, Math.toRadians(270)), Math.toRadians(360))
                .setTangent(Math.toRadians(90))
                .splineToConstantHeading(new Vector2d(36, -12), Math.toRadians(90))
                .splineToConstantHeading(new Vector2d( 48, -10), Math.toRadians(90))
                .afterTime(0, elbow.action( -300, .75))
                .afterTime(0, slide.action( -300, 0.75))
                .waitSeconds(.1)
                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(45, -50), Math.toRadians(270), new TranslationalVelConstraint(10)) //pickup from wall
                .afterTime(0, claw.action(1))
                .waitSeconds(.5)
                .afterTime(0, elbow.action( -1165, 0.5))
//                .afterTime(0, slide.action( -1100, 0.5))
                .setTangent(Math.toRadians(45))
                .afterTime(0, slide.action(-1100, 0.5))
                .setTangent(Math.toRadians(45))


//                .setReversed(true)
//                .splineToSplineHeading(new Pose2d(0, -51, Math.toRadians(180)), Math.toRadians(180))
//                .splineToSplineHeading(new Pose2d(0,-29, Math.toRadians(180)), Math.toRadians(45)) //move to chamber
                .strafeToLinearHeading(new Vector2d(0, -27), Math.toRadians(90.1))
                .afterTime(0, claw.action(0))
                .afterTime(0, elbow.action( 0, 0.5))
                .afterTime(0, slide.action(0, 0.5))
                .waitSeconds(1)
                ;


//                .splineToSplineHeading(new Pose2d(30, -36, Math.toRadians(360)), Math.toRadians(360))
//                .setTangent(Math.toRadians(45))
//                .splineToSplineHeading(new Pose2d(x, -12,Math.toRadians(315)), Math.toRadians(45))
//                .setTangent(Math.toRadians(270))
//                .splineToSplineHeading(new Pose2d( 38, -40, Math.toRadians(270)), Math.toRadians(270))
//                .waitSeconds(1)
//                .splineToConstantHeading(new Vector2d(x, y),Math.toRadians(270),new TranslationalVelConstraint(10))
//                .waitSeconds(3);


                //.splineToConstantHeading(new Vector2d(40, -50), Math.toRadians(90))
                //.waitSeconds(3);
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
//                    elbow.action( -1165, 0.5),
//                    slide.action(-920, 0.5),
//                    new SequentialAction(
//                        new ParallelAction(claw.action(0),
//                            claw.action(0),actionDriveToBar),
//                            elbow.action( -950, 0.4),
////                            slide.action(0, 0.3)),
//                    actionWait,elbow.action( -800, 0.5),
//                        actionWait,claw.action(1)
//                )
//            ));


        }  else {
            throw new RuntimeException();
        }
    }
}



























//    TrajectoryActionBuilder trajectoryActionBuilderMez2= drive.actionBuilder(beginPose)
//
//        .afterTime(0,claw.action(1)) //close claw
//        .afterTime(0, elbow.action( -1165, 0.5))
//        .afterTime(0, slide.action(-1100, 0.5))
//        .waitSeconds(3)
//        .splineToConstantHeading(new Vector2d( 0,-31), Math.toRadians(90)) //move to chamber
//        .waitSeconds(1)
//        .afterTime(0, claw.action(0)) //begins when the set reversed begins
//        .setReversed(true)
//        .splineToConstantHeading(new Vector2d(0,-50), Math.toRadians(270))  //back up from chamber
//        .afterTime(0, elbow.action( -800, 1))
//        .afterTime(0, claw.action(0))
//        .afterTime(0, elbow.action( 0, 0.5))
//        .afterTime(0, slide.action(0, 0.5))
//        .setTangent(Math.toRadians(360))
//        .setReversed(false)
//        .strafeTo(new Vector2d(40,-35))
//        .strafeTo(new Vector2d(44, -12))
//        .turnTo(Math.toRadians(270))
//        .afterTime(0, elbow.action( -300, 0.5))
//        .afterTime(0, slide.action( -300, 0.5))
//        .strafeTo(new Vector2d(54,-52),new TranslationalVelConstraint(10))
//
//        .afterTime(0,claw.action(1))
//        .waitSeconds(1)
//        .afterTime(0, elbow.action( -1165, 0.5))
//        .afterTime(1, slide.action( -1100, 0.5))
//        .setReversed(true)
//        .splineToConstantHeading(new Vector2d(x,y-6), Math.toRadians(90))
//        .splineToSplineHeading(new Pose2d(4,-31, Math.toRadians(90)), Math.toRadians(90))
//        .waitSeconds(1)
//        ;