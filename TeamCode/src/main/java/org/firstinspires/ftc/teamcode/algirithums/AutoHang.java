package org.firstinspires.ftc.teamcode.algirithums;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.Robot.Claw;
import org.firstinspires.ftc.teamcode.Robot.Elbow;
import org.firstinspires.ftc.teamcode.Robot.Hang;
import org.firstinspires.ftc.teamcode.Robot.Slide;
import org.firstinspires.ftc.teamcode.Robot.Wrist;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class AutoHang
{
    MecanumDrive drive;
    Slide slide;
    Elbow elbow;
    Hang hang;
    Wrist wrist;
    Claw claw;

    public AutoHang(MecanumDrive drive, Slide slide, Elbow elbow, Hang hang, Wrist wrist, Claw claw)
    {
        this.drive = drive;
        this.slide = slide;
        this.elbow = elbow;
        this.hang = hang;
        this.wrist = wrist;
        this.claw = claw;
    }

    public void prepare()
    {
        wrist.driveServo(0);
        slide.encoderControl(0,1);
        claw.driveServo(0);
        hang.encoderControl(-6500,1);
        elbow.encoderControl(-3300,1);
    }
    public void hang() throws InterruptedException
    {
        hang.encoderControl(-150,1);
        Thread.sleep(3000);
        elbow.encoderControl(-1700,1);
    }
}
