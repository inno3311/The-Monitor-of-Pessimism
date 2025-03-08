package org.firstinspires.ftc.teamcode.algirithums;

public class MotorTicksConversion
{
    /**
     * Yellow Jacket Planetary Gear Motor 435 rpm. Ticks per revolution = 384.5
     * @return
     */
    public double linearSlideInCM()
    {
        double ticksToSomething = 384.5 / (4 * Math.PI); // 30.61 cm //77.71 in
        return ticksToSomething;
    }

    /**
     * Yellow Jacket Planetary Gear Motor 435 rpm. Ticks per revolution = 384.5 with a 28:1 gear ratio
     * @return
     */
    public double elbowInDegrees()
    {
        double ticksToDegrees = (384.5 * 28) / 360; // 29.91
        return ticksToDegrees;
    }

    /**
     * Yellow Jacket Planetary Gear Motor 435 rpm. Ticks per revolution = 384.5 with a 28:1 gear ratio
     * @return
     */
    public double elbowInRadians()
    {
        double ticksToRadians = Math.toRadians(elbowInDegrees()); // ???
        return ticksToRadians;
    }

}
