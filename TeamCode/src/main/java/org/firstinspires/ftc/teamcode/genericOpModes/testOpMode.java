package org.firstinspires.ftc.teamcode.genericOpModes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.MotorControllers.RunToPosition;

@Autonomous(name = "RunToPos")
@Disabled
public class testOpMode extends LinearOpMode
{
    // 1in = 41.6 ticks
    // 1deg = 10

    RunToPosition runToPosition;

    @Override
    public void runOpMode() throws InterruptedException
    {
        runToPosition = new RunToPosition(hardwareMap, telemetry);

        waitForStart();

        runToPosition.turn(-10*45, 0.5);

        runToPosition.forward(42*24, 0.5);

        runToPosition.turn(10*90, 0.5);
    }
}
