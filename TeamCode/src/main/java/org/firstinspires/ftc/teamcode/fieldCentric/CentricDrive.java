package org.firstinspires.ftc.teamcode.fieldCentric;

import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

public class CentricDrive
{
    Telemetry telemetry;
    MecanumDrive driveController;
    private boolean flag;
    private double lastChanged = 0;

    public CentricDrive(MecanumDrive driveController, Telemetry telemetry)
    {
        this.driveController = driveController;
        this.telemetry = telemetry;
    }

    public void drive(double x,double y, double robot_heading, double slowMo, double turn)
    {
        //double speed = 1 * (1-Range.clip(slowMo, 0, 0.7));
        double speed = (2*Math.pow(slowMo-1.08*(Math.sqrt(0.3)), 2)) + 0.3;
        double drive_y = y * Math.cos(Math.toRadians(robot_heading)) + x * Math.sin(Math.toRadians(robot_heading));
        double drive_x = -y * Math.sin(Math.toRadians(robot_heading)) + x * Math.cos(Math.toRadians(robot_heading));
        driveController.driveMotors(-drive_y, turn, drive_x, speed);
        telemetry.update();
    }

    public double whichTurnMode(double turnToHeading, double turnBasic, boolean whichTurn, double time)
    {
        if (whichTurn && lastChanged < time)
        {
            flag = !flag;
            lastChanged = time + 0.25;
        }

        if (flag)
        {
            return turnToHeading;
        }
        else
        {
            return turnBasic;
        }
    }



}


