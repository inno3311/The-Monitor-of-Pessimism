package org.firstinspires.ftc.teamcode.sensors;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TouchSensorParent
{
    TouchSensor sensor;
    private String sensorName;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;

    public TouchSensorParent(String sensorName, LinearOpMode linearOpMode)
    {
        this.hardwareMap = linearOpMode.hardwareMap;
        this.telemetry = linearOpMode.telemetry;
        this.sensorName = sensorName;

        sensor = hardwareMap.get(TouchSensor.class, sensorName);
    }

    protected boolean getState()
    {
        return sensor.isPressed();
    }

    protected void getTelemetry()
    {
        telemetry.addData(sensorName, "State: " + sensor.isPressed());
    }
}
