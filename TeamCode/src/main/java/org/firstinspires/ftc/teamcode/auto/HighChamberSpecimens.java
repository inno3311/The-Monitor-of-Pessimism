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
        claw = new Claw(this);

        Pose2d beginPose = new Pose2d(10, -55, Math.toRadians(90));
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            waitForStart();


                int x = 44;
                int y = -48;
            //Mesloh attempt to use afterDisp to hang a specs.
            TrajectoryActionBuilder trajectoryActionBuilderTwoChamberRun= drive.actionBuilder(beginPose)

//
                .afterTime(0,claw.action(1)) //close claw
           .afterTime(0, elbow.action( -1165, 0.5))
           .afterTime(0, elbow.action(-1100, 0.5))
                .waitSeconds(1)
                .splineToConstantHeading(new Vector2d( 10,-29), Math.toRadians(90)) //move to chamber
           .afterTime(0, claw.action(0)) //begins when the action after it begins
//                .waitSeconds(.1)
//                .setTangent(Math.toRadians(0))
//                .splineToSplineHeading(new Pose2d(19, -30, Math.toRadians(180)), Math.toRadians(0))  //head right while rotating
////           .afterTime(0, protoSlideTheta.action( 0, 0.5))
////           .afterTime(0, protoLinearSlide.action(0, 0.5))
//                .splineToSplineHeading(new Pose2d(30, -30, Math.toRadians(270)), Math.toRadians(360))//.setReversed(true)
//                .splineToConstantHeading(new Vector2d(36, -12), Math.toRadians(90))//.setReversed(true)
//                .splineToConstantHeading(new Vector2d(46, -12), Math.toRadians(0))//.setReversed(true)
////           .afterTime(0, protoSlideTheta.action( -300, .75))
////           .afterTime(0, protoLinearSlide.action( -300, 0.75))
//                .splineToConstantHeading(new Vector2d(48, -40), Math.toRadians(270), new TranslationalVelConstraint(100))  //push sample
//                .splineToConstantHeading(new Vector2d(48, -50), Math.toRadians(270), new TranslationalVelConstraint(10)) //slow down for pickup
////           .afterTime(0, claw.action(1))
////           .afterTime(0, protoSlideTheta.action( -1165, 0.5))
////                .afterTime(0, protoLinearSlide.action( -1100, 0.5))
//                .waitSeconds(.1)
//                .setTangent(Math.toRadians(45))
//                .setReversed(true)
//                .splineToSplineHeading(new Pose2d(10,-29, Math.toRadians(90)), Math.toRadians(90)) //move to chamber
////           .afterTime(0, claw.action(0))
////           .afterTime(0, protoSlideTheta.action( 0, 0.5))
////           .afterTime(0, protoLinearSlide.action(0, 0.5))
//                .waitSeconds(.1)
                ;


            Action redRun = trajectoryActionBuilderTwoChamberRun
                .build();

            Actions.runBlocking(redRun);


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