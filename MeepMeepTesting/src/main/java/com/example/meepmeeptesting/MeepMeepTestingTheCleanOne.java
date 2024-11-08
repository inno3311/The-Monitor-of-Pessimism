package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTestingTheCleanOne
{
   public static void main(String[] args) {
      MeepMeep meepMeep = new MeepMeep(800);
              int a = 0;
              int b = -32;
              int x = 47;
              int y = -53;

      RoadRunnerBotEntity bucketRun = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15).build();

      bucketRun.runAction(bucketRun.getDrive().actionBuilder(new Pose2d(-40, -45, Math.toRadians(0)))
              .splineToSplineHeading(new Pose2d(-55,-55, Math.toRadians(225)), Math.toRadians(225))
              .build());

       RoadRunnerBotEntity chamberRun = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15).build();

       chamberRun.runAction(bucketRun.getDrive().actionBuilder(new Pose2d(10, -40, Math.toRadians(270)))
               .splineToSplineHeading(new Pose2d(0,-29, Math.toRadians(90)), Math.toRadians(90))
               .build());

      meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
              .setDarkMode(true)
              .setBackgroundAlpha(0.95f)
              .addEntity(bucketRun)
              .start();
   }
}