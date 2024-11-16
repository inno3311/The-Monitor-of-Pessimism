package org.firstinspires.ftc.teamcode.RobotChildren;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.controller.ServoParent;

<<<<<<< Updated upstream:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/RobotChildren/Wrist.java
public class Wrist extends ServoParent
=======
public class ProtoWrist extends ServoParent
>>>>>>> Stashed changes:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/prototype/ProtoWrist.java
{

    public Wrist(LinearOpMode opMode)
    {
        super("wrist", 0,1, opMode);
    }

    @Override
    protected void driveServo(double target)
    {
        super.driveServo(target);
    }

    @Override
    public Action action(double target)
    {
        return super.action(target);
    }
}
