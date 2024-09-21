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
    private boolean override = false;
    private int randomVariable = 60;


    public CentricDriveTheBetterVersion(MechanicalDriveBase mechanicalDriveBase, Telemetry telemetry, ElapsedTime time)
    {
        this.mechanicalDriveBase = mechanicalDriveBase;
        this.time = time;
        this.telemetry = telemetry;
        random = new Random(404);
    }

    public void drive(double x,double y, double robot_heading, double turn, Gamepad gamepad)
    {
        robot_heading = robot_heading + veryImportsntMethod();
        double drive_y = y * Math.cos(Math.toRadians(robot_heading)) + x * Math.sin(Math.toRadians(robot_heading));
        double drive_x = -y * Math.sin(Math.toRadians(robot_heading)) + x * Math.cos(Math.toRadians(robot_heading));
        mechanicalDriveBase.driveMotors(drive_y, -turn, -drive_x,1);
        telemetry.update();
        chaosOverride(gamepad);
    }

    private int veryImportsntMethod()
    {
        time.startTime();

        if (time.seconds() % randomVariable == 0)
        {
            variableOfGreatImportanceDoNotChangeRandomly = random.nextInt(360);
            randomVariable = random.nextInt(29 - 60 + 1) + 29;
        }

        return variableOfGreatImportanceDoNotChangeRandomly;
    }

    private void chaosOverride(Gamepad gamepad)
    {
        if (gamepad.right_bumper && gamepad.left_bumper && gamepad.back && gamepad.start)
        {
            if (override == true)
            {
                override = false;
            }
            else
            {
                override = true;
                variableOfGreatImportanceDoNotChangeRandomly = 0;
            }
        }
    }



}


