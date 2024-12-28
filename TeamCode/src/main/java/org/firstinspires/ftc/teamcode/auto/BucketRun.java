package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Elbow;
import org.firstinspires.ftc.teamcode.Robot.Slide;
import org.firstinspires.ftc.teamcode.Robot.Wrist;
import org.firstinspires.ftc.teamcode.initialization.Initialization;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.TankDrive;
import org.firstinspires.ftc.teamcode.roadrunner.tuning.TuningOpModes;

@Autonomous(name="Bucket Run", group="Linear OpMode")
public final class BucketRun extends LinearOpMode
{



    Initialization initialization;

    Slide slide;
    TouchSensor slideLimit;
    Elbow elbow;
    TouchSensor elbowLimit;
    Wrist wrist;
    Claw claw;


    @Override
    public void runOpMode() throws InterruptedException
    {
        slide = new Slide(this);
        elbow = new Elbow(this);
        wrist = new Wrist(this);
        claw = new Claw(this);

        double CLAW_OPEN = 0.5;
        int CLAW_CLOSE = 0;

        int SLIDE_DEPLOY_HIGHT = -2275;


        slideLimit = hardwareMap.get(TouchSensor.class, "slideLimit");
        elbowLimit = hardwareMap.get(TouchSensor.class, "elbowLimit");

        initialization = new Initialization(slide, slideLimit, elbow, elbowLimit);

        Pose2d beginPose = new Pose2d(-35, -60, Math.toRadians(180));
        slide = new Slide(this);
        elbow = new Elbow(this);
        initialization.initialization();
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class))
        {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);

            waitForStart();


            TrajectoryActionBuilder yellow_drop = drive.actionBuilder(beginPose)
                .afterTime(0, claw.action(CLAW_CLOSE))
                .afterTime(0, slide.action(-2175, 1))
                .afterTime(0, elbow.action(-2400, 1))
                .strafeToLinearHeading(new Vector2d(-53, -53), Math.toRadians(225), new TranslationalVelConstraint(30))
                .waitSeconds(.2)
                .afterTime(0, wrist.action(0))
                .afterTime(.5, claw.action(CLAW_OPEN))
                .waitSeconds(1)

                /////////////////////////////////////////////////////////////////////////////////////////
                //end of the first sample drop
//
                .afterTime(0, wrist.action(.5))
                .turnTo(Math.toRadians(80))
                .afterTime(0, elbow.action(-300, 1))
                .afterTime(1.5, elbow.action(0, .5))
                .afterTime(0, slide.action(-1400, 1))  //changed from -1300
                .afterTime(1, wrist.action(0))
                .afterTime(1.5, claw.action(CLAW_CLOSE))
                .waitSeconds(2)
                .afterTime(0, claw.action(CLAW_CLOSE))
                .afterTime(0, slide.action(0, 1))
                .afterTime(0.5, elbow.action(-2400, 1))
                .afterTime(1, slide.action(SLIDE_DEPLOY_HIGHT, 1))  // changed from -2175
                .waitSeconds(2)
                .turnTo(Math.toRadians(225))
//                .waitSeconds(1)
                .afterTime(0, wrist.action(0))
                .afterTime(0.5, claw.action(CLAW_OPEN))
                .afterTime(0.6, wrist.action(.5))
                .waitSeconds(1)

//                /////////////////////////////////////////////////////////////////////////////////////////////
//                //end of second sample drop
//
////               .turnTo(Math.toRadians(64))
                .strafeToLinearHeading(new Vector2d(-41, -53), Math.toRadians(75), new TranslationalVelConstraint(20))
                .afterTime(0, elbow.action(-300, 1))
                .afterTime(1.5, elbow.action(0, .5))
                .afterTime(0, slide.action(-1500, 1))  //changed from -1400
                .afterTime(1, wrist.action(0))
                .afterTime(1.5, claw.action(CLAW_CLOSE))
                .waitSeconds(2)
                .afterTime(0, claw.action(CLAW_CLOSE))
                .afterTime(0, elbow.action(-2400, 1))
                .afterTime(.5, slide.action(0, 1))

                .afterTime(1, slide.action(-2175, 1))
                .waitSeconds(1.5)
//                .turnTo(Math.toRadians(225))
                .strafeToLinearHeading(new Vector2d(-53, -53), Math.toRadians(225))
//                .waitSeconds(1)
                .afterTime(0, wrist.action(0))
                .afterTime(0.5, claw.action(CLAW_OPEN))
                .afterTime(0.6, wrist.action(.5))
                .waitSeconds(1)
//

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //end of third drop

                .afterTime(0, wrist.action(0))
                //.afterTime(0, claw.action(CLAW_CLOSE))
                .afterTime(.2, slide.action(0, 1))
                .afterTime(.8, elbow.action(-400, 1))  //changed from -300
                .strafeToLinearHeading(new Vector2d(-56, -53), Math.toRadians(100))
//                .afterTime(0, slide.action(0, 1))
//                .afterTime(0, claw.action(CLAW_CLOSE))
//                .afterTime(.8, elbow.action(-400, 1))  //changed from -300
                .afterTime(0, claw.action(CLAW_OPEN))
                .afterTime(1, slide.action(-1550, 1))  //changed from -1400
                .afterTime(2, elbow.action(0, .5))
                .afterTime(2.2, claw.action(CLAW_CLOSE))

                .afterTime(3, slide.action(0, 1))
                .afterTime(3.5, elbow.action(-2500, 1))
                .waitSeconds(4)
                .strafeTo(new Vector2d(-45, -53))

                .afterTime(1, slide.action(-2175, 1))
                .strafeToLinearHeading(new Vector2d(-53, -53), Math.toRadians(225))
                .afterTime(0.5, elbow.action(-2400, 1))
                .afterTime(1, slide.action(-2175, 1))
                .waitSeconds(1)
                .afterTime(0, wrist.action(0))
                .afterTime(0.5, claw.action(CLAW_OPEN))
                .afterTime(0.6, wrist.action(.5))
                .waitSeconds(1)

//                .afterTime(0, wrist.action(0))
//                .strafeToLinearHeading(new Vector2d(-56, -53), Math.toRadians(100))
//                .afterTime(0, slide.action(0, 1))
//                .afterTime(0, claw.action(CLAW_CLOSE))
//                .afterTime(1, elbow.action(-400, 1))  //changed from -300
//                .afterTime(2, claw.action(CLAW_OPEN))
//                .afterTime(3, slide.action(-1500, 1))  //changed from -1400
//                .afterTime(4, elbow.action(0, .5))
//                .afterTime(4.2, claw.action(CLAW_CLOSE))
//
//                .afterTime(5, slide.action(0, 1))
//                .afterTime(5.5, elbow.action(-2400, 1))
//                .waitSeconds(6)
//                .strafeTo(new Vector2d(-45, -53))
//
//                .afterTime(1, slide.action(-2175, 1))
//                .strafeToLinearHeading(new Vector2d(-53, -53), Math.toRadians(225))
//                .afterTime(0.5, elbow.action(-2400, 1))
//                .afterTime(1, slide.action(-2175, 1))
//                .waitSeconds(1.5)
//                .afterTime(0, wrist.action(0))
//                .afterTime(0.5, claw.action(CLAW_OPEN))
//                .afterTime(0.6, wrist.action(.5))
//                .waitSeconds(1)

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //park
                .afterTime(0, wrist.action(0))
                .afterTime(.3, slide.action(0, 1))
                .strafeToLinearHeading(new Vector2d(-35, -10), Math.toRadians(180))
                .afterTime(0, elbow.action(-3200, 1))
                .strafeToLinearHeading(new Vector2d(-22, -10), Math.toRadians(180))
                .afterTime(0, wrist.action(.5))
                .waitSeconds(1)
                ;

            Action redRun = yellow_drop
                .build();

            Actions.runBlocking(redRun);

        } else if (TuningOpModes.DRIVE_CLASS.equals(TankDrive.class))
    {
        TankDrive drive = new TankDrive(hardwareMap, beginPose);

        waitForStart();

        Actions.runBlocking(
            drive.actionBuilder(beginPose)
                .splineTo(new Vector2d(30, 30), Math.PI / 2)
                .splineTo(new Vector2d(0, 60), Math.PI)
                .build());
    }
    else
    {
        throw new RuntimeException();
    }
    }
}

