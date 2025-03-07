package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
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

//      RoadRunnerBotEntity bucketRun = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15).build();

//      bucketRun.runAction(bucketRun.getDrive().actionBuilder(new Pose2d(-40, -45, Math.toRadians(0)))
//              .splineToSplineHeading(new Pose2d(-55,-55, Math.toRadians(225)), Math.toRadians(225))
//              .build());

       RoadRunnerBotEntity chamberRun2 = new DefaultBotBuilder(meepMeep).setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15).build();


       chamberRun2.runAction(chamberRun2.getDrive().actionBuilder(new Pose2d(10, -55, Math.toRadians(90)))
           .waitSeconds(.001) //todo wsa .5

//                .afterTime(0, claw.action(CLAW_CLOSE)) //close claw
//                .afterTime(0, wrist.action(1)) //close claw
//                .afterTime(0, elbow.action(ELBOW_HIGH_CHAMBER, 1))
//                .afterTime(0, slide.action(SLIDE_HIGH_CHAMBER, 1))
           .splineToConstantHeading(new Vector2d( 10,-27), Math.toRadians(90)) //move to chamber, hang #1 specimen

           ////////////////////////////////////////////////////////////////////////////////////
           /// Move to Left Floor Sample to Observation zone

//                .afterTime(0.01, claw.action(CLAW_OPEN)) //open claw
//                .afterTime(0, slide.action(0, 1))
//                .afterTime(0.3, elbow.action(0, 1))

           .setTangent(Math.toRadians(0))  //TODO  should we be doing this?
           .splineToSplineHeading(new Pose2d(30, -30, Math.toRadians(270)), Math.toRadians(360))//back away from the submersible
//                .afterTime(0, wrist.action(0.7))
           .splineToConstantHeading(new Vector2d(36, -12), Math.toRadians(90))  //move around submersible to
           .splineToConstantHeading(new Vector2d(48, -12), Math.toRadians(0))   //ready to push #1 to wall.
           .splineToConstantHeading(new Vector2d(48, -43), Math.toRadians(270), new TranslationalVelConstraint(25)) //slow down for sample drop off

           ////////////////////////////////////////////////////////////////////////////////////
           /// Push Center Floor Sample Observation zone
           /// Wall to Center Sample back to wall

           .setReversed(true)
           .splineToConstantHeading(new Vector2d(48, -10), Math.toRadians(90), new TranslationalVelConstraint(25)) //go fetch center sample
           .splineToConstantHeading(new Vector2d(60, -10), Math.toRadians(270), new TranslationalVelConstraint(25)) //move centered to center sample
//                .afterTime(0, elbow.action( ELBOW_TO_WALL, .75))
//                .afterTime(0, slide.action( SLIDE_TO_WALL, 0.75)) //raise and extend the arm to the position of the specimen on the wall
//                .afterTime(0, wrist.action(0.7))
           .splineToConstantHeading(new Vector2d(50, -38), Math.toRadians(270), new TranslationalVelConstraint(25)) //push center sample

           ////////////////////////////////////////////////////////////////////////////////////
           /// Pick up Specimen #2 from Wall and Hang it

           .splineToConstantHeading(new Vector2d(50, -48), Math.toRadians(270), new TranslationalVelConstraint(10)) //slow down for sample drop off and run into the specimen on the wall
//                .afterTime(0, claw.action(CLAW_CLOSE)) //close claw
           .waitSeconds(.2) //todo wsa .5
//                .afterTime(0, wrist.action(1))
//                .afterTime(0, elbow.action(ELBOW_HIGH_CHAMBER, 0.5))
           .waitSeconds(.2) //todo wsa .5
//                .afterTime(.5, slide.action(SLIDE_HIGH_CHAMBER, 0.5)) //raise and extend the arm to the height of the upper bar on the submersible
           .setReversed(true)
//                .afterTime(0, claw.action(CLAW_CLOSE)) //close claw
           .splineToSplineHeading(new Pose2d(8,-21, Math.toRadians(90)), Math.toRadians(90)) //move to chamber and hang spec
           .build());

      meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
              .setDarkMode(true)
              .setBackgroundAlpha(0.95f)
              .addEntity(chamberRun2)
              .start();
   }
}