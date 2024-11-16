package org.firstinspires.ftc.teamcode.RobotChildren;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.controller.ServoParent;

<<<<<<< Updated upstream:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/RobotChildren/Claw.java
public class Claw extends ServoParent
=======
import org.firstinspires.ftc.teamcode.controller.ServoParent;

public class ProtoClaw extends ServoParent
>>>>>>> Stashed changes:TeamCode/src/main/java/org/firstinspires/ftc/teamcode/prototype/ProtoClaw.java
{
    public Claw(LinearOpMode opMode)
    {
        super("claw", 0,1, opMode);
    }

    @Override
    public void driveServo(double target)
    {
        super.driveServo(target);
    }

    @Override
    public Action action(double target)
    {
        return super.action(target);
    }
}
