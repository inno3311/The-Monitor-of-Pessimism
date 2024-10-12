package org.firstinspires.ftc.teamcode.algirithums.samplePickup;

public class DeltaChange
{

    public DeltaChange()
    {

    }

    /**
     *
     * @param x is equal to the value returned by the open cv program. X is near far
     * @param z is equal to the value returned by the open cv program. Z is up down
     * @returns the distance to the sample in cm
     */
    protected double armLength(double x, double z)
    {
        double distanceToSample = Math.sqrt((Math.pow(x,2) + Math.pow(z, 2)));
        return distanceToSample;
    }

    protected double armAngle()
    {

        return 0.0;
    }



}
