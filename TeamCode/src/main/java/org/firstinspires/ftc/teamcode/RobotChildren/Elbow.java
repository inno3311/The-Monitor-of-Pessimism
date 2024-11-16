package org.firstinspires.ftc.teamcode.RobotChildren;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
<<<<<<< Updated upstream:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/RobotChildren/Elbow.java
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.controller.MotorParent;

public class Elbow extends MotorParent
=======
import org.firstinspires.ftc.teamcode.controller.MotorParent;

public class ProtoSlideTheta extends MotorParent
>>>>>>> Stashed changes:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/prototype/ProtoSlideTheta.java
{

    public Elbow(LinearOpMode opMode)
    {
        super("elbow", true, true, opMode);
    }

    @Override
    protected void analogControl(double speedLimit, double input, boolean advanceBreak, boolean slowMode, boolean lowerLimit, boolean upperLimit)
    {
        super.analogControl(speedLimit, input, advanceBreak, slowMode, lowerLimit, upperLimit);
    }

    @Override
    public Action action(int target, double speed)
    {
        return super.action(target, speed);
    }

    @Override
    public void initialize(TouchSensor sensor, int direction, double speed) {super.initialize(sensor, direction, speed);}

    @Override
    protected void automaticEncoderReset(boolean reset) {super.automaticEncoderReset(reset);}

    @Override
    public void resetEncoder() {super.resetEncoder();}

    public enum Presets
    {
        TOP_CHAMBER,
        BOTTOM_CHAMBER,
        TOP_BUCKET,
        BOTTOM_BUCKET,
        PICKUP_FLOOR,
        PICKUP_WALL,
        INITIALIZATION
    }

    public void encoderPresets(Presets preset)
    {
        switch (preset)
        {
            case TOP_CHAMBER:
                super.encoderControl(-1165,1);
                break;
            case BOTTOM_CHAMBER:
                super.encoderControl(-525,1);
                break;
            case TOP_BUCKET:
                super.encoderControl(-2050,1);
                break;
            case BOTTOM_BUCKET:
                super.encoderControl(-2030,1);
                break;
            case PICKUP_FLOOR:
                super.encoderControl(0,1);
                break;
            case PICKUP_WALL:
                super.encoderControl(10,1);
                break;
            case INITIALIZATION:
                super.encoderControl(-1175,1);
            default:
                break;
        }
    }

    @Override
    public void telemetry()
    {
        super.telemetry();
    }
}
