package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import org.firstinspires.ftc.teamcode.RobotChildren.Claw;
import org.firstinspires.ftc.teamcode.RobotChildren.Elbow;
import org.firstinspires.ftc.teamcode.RobotChildren.Slide;
import org.firstinspires.ftc.teamcode.RobotChildren.Wrist;
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

    public void align(double target)
    {
        drive.updatePoseEstimate();

        TrajectoryActionBuilder pickup = drive.actionBuilder(new Pose2d(0,0,0))
            .strafeTo(new Vector2d(0,2));
            //.strafeTo(new Vector2d(drive.pose.position.x,drive.pose.position.y+target));



        Action pickupAction = pickup.build();

        Actions.runBlocking(pickupAction);

    }

}
