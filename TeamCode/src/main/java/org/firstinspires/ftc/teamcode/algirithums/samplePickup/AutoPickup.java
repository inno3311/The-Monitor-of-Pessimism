package org.firstinspires.ftc.teamcode.algirithums.samplePickup;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class AutoPickup
{
    MecanumDrive drive;
    Slide slide;
    Elbow elbow;
    Wrist wrist;
    Claw claw;

    public AutoPickup(MecanumDrive drive/*, Slide slide, Elbow elbow, Wrist wrist, Claw claw*/)
    {
        this.drive = drive;
//        this.slide = slide;
//        this.elbow = elbow;
//        this.wrist = wrist;
//        this.claw = claw;
    }

    public void align(double x_target, double y_target)
    {

//        TrajectoryActionBuilder pickup = drive.actionBuilder(drive.pose/*new Pose2d(0,0, Math.toRadians(90))*/)
//                .strafeTo(new Vector2d(drive.pose.position.x,drive.pose.position.y+target));

        TrajectoryActionBuilder pickup = drive.actionBuilder(new Pose2d(0,0,Math.toRadians(90)))
                .strafeTo(new Vector2d(x_target,y_target)     //    .strafeToConstantHeading(new Vector2d(-x_target, y_target)
                                );


        Action pickupAction = pickup.build();

        Actions.runBlocking(pickupAction);

    }

    public double align_angle(double delta_x, double arm_length)
    {
        return(Math.atan2(arm_length, delta_x));
    }

}
