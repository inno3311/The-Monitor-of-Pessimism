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
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.teamcode.initialization.Initialization;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.tuning.TuningOpModes;

@Autonomous(name="SpecimenRun2", group="Linear OpMode")
public final class HighChamberSpecimens extends LinearOpMode {

    Initialization initialization;

    Slide slide;
    TouchSensor slideLimit;
    Elbow elbow;
    TouchSensor elbowLimit;
    Wrist wrist;
    Claw claw;


    @Override
    public void runOpMode() throws InterruptedException {

        slide = new Slide(this);
        elbow = new Elbow(this);
        wrist = new Wrist(this);
        claw = new Claw(this);

        double CLAW_OPEN = 0.5;
        int CLAW_CLOSE = 0;

        int EBLOW_TO_WALL = -250;

        slideLimit = hardwareMap.get(TouchSensor.class, "slideLimit");
        elbowLimit = hardwareMap.get(TouchSensor.class, "elbowLimit");


        initialization = new Initialization(slide, slideLimit, elbow, elbowLimit);

        Pose2d beginPose = new Pose2d(10, -55, Math.toRadians(90));
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            initialization.initialization();

            waitForStart();


            TrajectoryActionBuilder trajectoryActionBuilderTwoChamberRun = drive.actionBuilder(beginPose)
                .afterTime(0,claw.action(CLAW_CLOSE)) //close claw
                .afterTime(0, elbow.action(-1165, 0.5))
                .afterTime(0, slide.action(-1100, 0.5))
                .waitSeconds(1)  //TODO trim down this number
                .splineToConstantHeading(new Vector2d( 10,-27.5), Math.toRadians(90)) //move to chamber, hang #1 specimen
                .afterTime(0, claw.action(CLAW_OPEN)) //open claw
                .afterTime(0.3, elbow.action(0, 0.5))
                .afterTime(0, slide.action(0, 0.5))
                .waitSeconds(.1)
                .setTangent(Math.toRadians(0))  //TODO  should we be doing this?
                .splineToSplineHeading(new Pose2d(30, -30, Math.toRadians(270)), Math.toRadians(360))//back away from the submersible
                .splineToConstantHeading(new Vector2d(36, -12), Math.toRadians(90))  //move around submersible to
                .splineToConstantHeading(new Vector2d(48, -12), Math.toRadians(0))   //ready to push #1 to wall.
                .splineToConstantHeading(new Vector2d(48, -43), Math.toRadians(270), new TranslationalVelConstraint(25)) //slow down for sample drop off
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(48, -10), Math.toRadians(90), new TranslationalVelConstraint(25)) //go fetch center sample
                .splineToConstantHeading(new Vector2d(60, -10), Math.toRadians(270), new TranslationalVelConstraint(25)) //move centered to center sample
                .afterTime(0, elbow.action( EBLOW_TO_WALL, .75))
                .afterTime(0, slide.action( -400, 0.75)) //raise and extend the arm to the position of the specimen on the wall
                .afterTime(0, wrist.action(0.7))
                .splineToConstantHeading(new Vector2d(50, -38), Math.toRadians(270), new TranslationalVelConstraint(25)) //push center sample
                .splineToConstantHeading(new Vector2d(50, -48), Math.toRadians(270), new TranslationalVelConstraint(20)) //slow down for sample drop off and run into the specimen on the wall
                .afterTime(0, claw.action(CLAW_CLOSE)) //close claw
                .waitSeconds(.5)
                .afterTime(0, wrist.action(1))
                .afterTime(0, elbow.action( -1165, 0.5))
                .waitSeconds(.5)
                .afterTime(.5, slide.action( -1100, 0.5)) //raise and extend the arm to the height of the upper bar on the submersible
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(10,-26, Math.toRadians(90)), Math.toRadians(90)) //move to chamber and hang spec
                .afterTime(0, claw.action(CLAW_OPEN)) //open claw to release the specimen that is on the bar
                .afterTime(0.0, slide.action( 0, 1)) //raise and extend the arm to the position of the specimen on the wall
                .waitSeconds(1)
                .setReversed(true)
                .afterTime(.5, slide.action( -400, 1)) //raise and extend the arm to the position of the specimen on the wall
                .afterTime(0, wrist.action(0.7))
                .afterTime(0.2, elbow.action( EBLOW_TO_WALL, .75))

                .setTangent(Math.toRadians(0))
                //.splineToSplineHeading(new Pose2d(30, -35, Math.toRadians(270)), Math.toRadians(360))//back away from the submersible
                .splineToSplineHeading(new Pose2d(30, -35, Math.toRadians(270)), Math.toRadians(0))//back away from the submersible
                .splineToConstantHeading(new Vector2d(50, -38), Math.toRadians(270)) //go to pick up the second specimen from the wall
                .splineToConstantHeading(new Vector2d(50, -48), Math.toRadians(270), new TranslationalVelConstraint(20)) //slow down run into the specimen on the wall

                .afterTime(0, claw.action(CLAW_CLOSE)) //close claw
                .waitSeconds(.5)
                .setReversed(true)
                .afterTime(0, wrist.action(1))
                .afterTime(0, elbow.action( -1165, 0.5))
                .afterTime(.5, slide.action( -1100, 0.5)) //raise and extend the arm to the height of the upper bar on the submersible
                .splineToSplineHeading(new Pose2d(10,-26, Math.toRadians(90)), Math.toRadians(90)) //move to chamber
                .afterTime(0, claw.action(CLAW_OPEN)) //open claw to release the specimen that is on the bar
                .setReversed(true)
                .splineToConstantHeading(new Vector2d(50, -48), Math.toRadians(270)) //park
                ;


            Action redRun = trajectoryActionBuilderTwoChamberRun
                .build();

            TrajectoryActionBuilder chamberCycle = drive.actionBuilder(beginPose)
                .waitSeconds(.5)
                .afterTime(0, claw.action(CLAW_CLOSE))
                .afterTime(0, elbow.action(-1165, 1))
                .afterTime(0, slide.action(-1100, 1))
                .splineToConstantHeading(new Vector2d( 10,-29), Math.toRadians(90)) //move to chamber
                .setTangent(Math.toRadians(0))
                .setReversed(true)
                .afterTime(0,claw.action(CLAW_OPEN))
                .afterTime(0, wrist.action(0.7))
                .afterTime(0.2, elbow.action( EBLOW_TO_WALL, .75))
                .afterTime(0.2, slide.action( -200, 0.75)) //raise and extend the arm to the position of the specimen on the wall
                .strafeToLinearHeading(new Vector2d(40, -45), Math.toRadians(270), new TranslationalVelConstraint(100))
                .waitSeconds(0.5)
                .splineToConstantHeading(new Vector2d( 40,-53), Math.toRadians(270))
                .afterTime(0,claw.action(CLAW_CLOSE))
                .waitSeconds(0.5)
                //.afterTime(.5, elbow.action( 0, 1))
                //.afterTime(.5, slide.action( 0, 1)) //raise and extend the arm to the height of the upper bar on the submersible
                .afterTime(0, elbow.action( /*-1165*/-1100, 1))
                .afterTime(.5, slide.action( -1200, 1)) //raise and extend the arm to the height of the upper bar on the submersible
                .strafeToLinearHeading(new Vector2d(0, -42),Math.toRadians(89.99))
                .splineToConstantHeading(new Vector2d( 0,-28), Math.toRadians(90),new TranslationalVelConstraint(100)) //move to chamber



//                .setTangent(Math.toRadians(0))
//                .setReversed(true)
//                .afterTime(0,claw.action(CLAW_OPEN))
//                .afterTime(0, wrist.action(0.7))
//                .afterTime(0.2, elbow.action( EBLOW_TO_WALL, .75))
//                .afterTime(0.2, slide.action( -200, 0.75)) //raise and extend the arm to the position of the specimen on the wall
//                .strafeToLinearHeading(new Vector2d(40, -45), Math.toRadians(270), new TranslationalVelConstraint(100))
//                .waitSeconds(0.5)
//                .splineToConstantHeading(new Vector2d( 40,-53), Math.toRadians(270))
//                .afterTime(0,claw.action(CLAW_CLOSE))
//                .waitSeconds(0.5)
//                //.afterTime(.5, elbow.action( 0, 1))
//                //.afterTime(.5, slide.action( 0, 1)) //raise and extend the arm to the height of the upper bar on the submersible
//                .afterTime(0, elbow.action( /*-1165*/-1100, 1))
//                .afterTime(.5, slide.action( -1200, 1)) //raise and extend the arm to the height of the upper bar on the submersible
//                .strafeToLinearHeading(new Vector2d(0, -42),Math.toRadians(89.99))
//                .splineToConstantHeading(new Vector2d( 0,-28), Math.toRadians(90),new TranslationalVelConstraint(100)) //move to chamber
                ;

            Action chamberCycleAction = chamberCycle
                .build();

            Actions.runBlocking(redRun /*chamberCycleAction*/);


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