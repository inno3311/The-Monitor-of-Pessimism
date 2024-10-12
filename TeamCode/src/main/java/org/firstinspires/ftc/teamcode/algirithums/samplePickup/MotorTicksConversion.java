package org.firstinspires.ftc.teamcode.algirithums.samplePickup;

public class MotorTicksConversion
{

    public MotorTicksConversion()
    {

    }

    /**
     * Yellow Jacket Planetary Gear Motor 435 rpm. Ticks per revolution = 384.5
     * @return
     */
    public double linearSlideInCM()
    {
        double ticksToSomething = 384.5 * ((double) 1 /30) ; // 12.8167
        return ticksToSomething;
    }

    /**
     * Yellow Jacket Planetary Gear Motor 435 rpm. Ticks per revolution = 384.5 with a 28:1 gear ratio
     * @return
     */
    public double ThetaInRadians()
    {

        return 0;
    }

}
