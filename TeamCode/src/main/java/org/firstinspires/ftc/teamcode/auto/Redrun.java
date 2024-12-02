package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.aprilTags.AprilTagMaster;
import org.firstinspires.ftc.teamcode.RobotChildren.Claw;
import org.firstinspires.ftc.teamcode.RobotChildren.Elbow;
import org.firstinspires.ftc.teamcode.RobotChildren.Wrist;
import org.firstinspires.ftc.teamcode.RobotChildren.Slide;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.roadrunner.tuning.TuningOpModes;


//@Autonomous(name="SpecimenRun", group="Linear OpMode")
public final class Redrun extends LinearOpMode {



    Elbow elbow;
    Slide slide;

    Wrist wrist;
    //ProtoClawLeft clawLeft;
    Claw claw;
    AprilTagMaster aprilTag;


    @Override
    public void runOpMode() throws InterruptedException {
        slide = new Slide(this);
        elbow = new Elbow(this);
        wrist = new Wrist(this);
        //clawLeft = new ProtoClawLeft(this);
        claw = new Claw(this);
        aprilTag = new AprilTagMaster(hardwareMap);
        Pose2d beginPose = new Pose2d(aprilTag.getFieldX(), aprilTag.getFieldY(), aprilTag.getFieldYaw());
        if (TuningOpModes.DRIVE_CLASS.equals(MecanumDrive.class)) {
            MecanumDrive drive = new MecanumDrive(hardwareMap, beginPose);
            int a = 0;
            int b = -35;
            int x = 47;
            int y = -53;
            waitForStart();

            Actions.runBlocking(
                drive.actionBuilder(beginPose)

                        .afterTime(0,elbow.action(-2050, 1))
//                .afterTime(0,slide.action(-2175, 0.5))
                        .waitSeconds(3)
                        .turnTo(Math.toRadians(225))
                        .waitSeconds(2)
                        .strafeTo(new Vector2d(-55,-55))
                        //.splineToSplineHeading(new Pose2d(-55,-55, Math.toRadians(225)), Math.toRadians(225))
                        .afterTime(0,claw.action(0))
                        .waitSeconds(1)

//                    .splineTo(new Vector2d(48.0, -48), Math.toRadians(0))
//                    .splineTo(new Vector2d(48.0, 48), Math.toRadians(90))
//                    .splineTo(new Vector2d(-48.0, 48), Math.toRadians(180))
//                    .splineTo(new Vector2d(-48.0, -48), Math.toRadians(270))

//                    .splineToConstantHeading(new Vector2d(a, b), Math.toRadians(90))
//                    .waitSeconds(1) .setReversed(true)//space for program to hook specimen on bar
//                    .splineToSplineHeading(new Pose2d(30, -35, Math.toRadians(180)), Math.toRadians(0))





//                    .splineToSplineHeading(new Pose2d(42, -10, Math.toRadians(270)), Math.toRadians(360))
//                    .splineToConstantHeading(new Vector2d(46, -20), Math.toRadians(270))
//                    .splineToConstantHeading(new Vector2d(x, y), Math.toRadians(270))
//                    .waitSeconds(1) //space for program that obtains specimen
//                    .splineToConstantHeading(new Vector2d(x, y+3), Math.toRadians(90))
//                    .splineToSplineHeading(new Pose2d(a+1, b, Math.toRadians(90)), Math.toRadians(90)) //end of first lap

//                    .waitSeconds(1) //space for program to hook specimen on bar
//                    .splineToSplineHeading(new Pose2d(30, -40, Math.toRadians(270)), Math.toRadians(360))
//                    .splineToSplineHeading(new Pose2d(54, -10, Math.toRadians(270)), Math.toRadians(360))
//                    .splineToConstantHeading(new Vector2d(56, -20), Math.toRadians(270))
//                    .splineToConstantHeading(new Vector2d(x, y), Math.toRadians(270))
//                    .waitSeconds(1) //space for program that obtains specimen
//                    .splineToConstantHeading(new Vector2d(x, y+3), Math.toRadians(90))
//                    .splineToSplineHeading(new Pose2d(a+2, b, Math.toRadians(90)), Math.toRadians(90)) //end of second lap
//
//                    .waitSeconds(1) //space for program to hook specimen on bar
//                    .splineToSplineHeading(new Pose2d(30, -40, Math.toRadians(270)), Math.toRadians(360))
//                    .splineToSplineHeading(new Pose2d(58, -10, Math.toRadians(270)), Math.toRadians(360))
//                    .splineToConstantHeading(new Vector2d(61, -20), Math.toRadians(270))
//                    .splineToConstantHeading(new Vector2d(61, y), Math.toRadians(270)) //this line may not be necessary if robot can catch the sample on its side
//                    .splineToConstantHeading(new Vector2d(x,y), Math.toRadians(180))
//                    .waitSeconds(1) //space for program that obtains specimen
//                    .splineToConstantHeading(new Vector2d(x, y+3), Math.toRadians(90))
//                    .splineToSplineHeading(new Pose2d(a+2, b, Math.toRadians(90)), Math.toRadians(90)) //end of second lap
//                    .waitSeconds(1) //space for program to hook specimen on bar
//                    .splineToConstantHeading(new Vector2d(53, -58), Math.toRadians(270))
                    .build());


//
        }  else {
            throw new RuntimeException();
        }
    }
}
