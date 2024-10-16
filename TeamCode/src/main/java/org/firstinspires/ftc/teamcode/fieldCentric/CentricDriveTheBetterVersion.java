package org.firstinspires.ftc.teamcode.fieldCentric;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.controller.MechanicalDriveBase;

import java.util.Random;

public class CentricDriveTheBetterVersion
{
    Telemetry telemetry;
    MechanicalDriveBase mechanicalDriveBase;
    ElapsedTime time;
    Random random;
    private int variableOfGreatImportanceDoNotChangeRandomly = 0;

    private int variableOfLesserImportance = 0;
    private boolean override = false;
    private int randomVariable = 30;

    private double flag = 0;


    public CentricDriveTheBetterVersion(MechanicalDriveBase mechanicalDriveBase, Telemetry telemetry, ElapsedTime time)
    {
        this.mechanicalDriveBase = mechanicalDriveBase;
        this.time = time;
        this.telemetry = telemetry;
        random = new Random();
        time.startTime();
    }

    public void drive(double x,double y, double robot_heading, double turn, Gamepad gamepad)
    {
        chaosOverride(gamepad);
        int offset = veryImportsntMethod();
        robot_heading = robot_heading + offset;
        double drive_y = y * Math.cos(Math.toRadians(robot_heading)) + x * Math.sin(Math.toRadians(robot_heading));
        double drive_x = -y * Math.sin(Math.toRadians(robot_heading)) + x * Math.cos(Math.toRadians(robot_heading));
        mechanicalDriveBase.driveMotors(drive_y, -turn, -drive_x,1);
        telemetry.update();

    }

    private int veryImportsntMethod()
    {

        telemetry.addData("randomVariable", randomVariable);
        telemetry.addData("time", time.seconds());

        if (randomVariable < time.seconds())
        {
            time.reset();
            variableOfGreatImportanceDoNotChangeRandomly = random.nextInt(360);
            randomVariable = random.nextInt(45 - 25 + 1) + 25;
        }

        telemetry.addData("Offset ", variableOfGreatImportanceDoNotChangeRandomly);

        return variableOfGreatImportanceDoNotChangeRandomly;
    }

    private void chaosOverride(Gamepad gamepad)
    {
        if (gamepad.back && flag < time.seconds())
        {
            flag = time.seconds() + 1;
            if (override)
            {
                override = false;
                variableOfGreatImportanceDoNotChangeRandomly = variableOfLesserImportance;
            }
            else
            {
                override = true;
                variableOfLesserImportance = variableOfGreatImportanceDoNotChangeRandomly;
                variableOfGreatImportanceDoNotChangeRandomly = 0;

            }
        }
        telemetry.addData("Override",override);
    }



}


