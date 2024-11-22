package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.Arclength;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Pose2dDual;
import com.acmerobotics.roadrunner.PosePath;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.VelConstraint;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import org.jetbrains.annotations.NotNull;

public class MeepMeepTesting {
   public static void main(String[] args) {
      MeepMeep meepMeep = new MeepMeep(800);
              int a = 0;
              int b = -32;
              int x = 47;
              int y = -53;
      RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
              // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
              .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
              .build();

      RoadRunnerBotEntity myBot2 = new DefaultBotBuilder(meepMeep)
              // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
              .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
              .build();

      RoadRunnerBotEntity myBotSam = new DefaultBotBuilder(meepMeep)
              // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
              .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
              .build();


       RoadRunnerBotEntity myRedHang = new DefaultBotBuilder(meepMeep)
           // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
           .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
           .build();




      myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-36, -55, Math.toRadians(90)))
              //.waitSeconds(2)
              .lineToY(-34)
              .lineToY(-40)
              .strafeTo(new Vector2d(-54, -40))
              .setTangent(Math.toRadians(90))
              .lineToYSplineHeading(-10, Math.toRadians(0))
              .setTangent(Math.toRadians(0))
              .lineToXSplineHeading(20, Math.toRadians(0))
              .splineToConstantHeading(new Vector2d(50,-36),0)
              .waitSeconds(2)
              .setReversed(true)
              .splineToConstantHeading(new Vector2d(20,-10),Math.toRadians(180))
              .setTangent(Math.toRadians(180))
              .lineToX(-60)
              .waitSeconds(2)
              .setTangent(Math.toRadians(0))
              .lineToXSplineHeading(20, Math.toRadians(0))
              .splineToConstantHeading(new Vector2d(50,-36),0)
              .waitSeconds(2)
              .build());

      myBot2.runAction(myBot2.getDrive().actionBuilder(new Pose2d(-36, 60, Math.toRadians(270)))
              .lineToYSplineHeading(33, Math.toRadians(0))
              .waitSeconds(2)
              .setTangent(Math.toRadians(90))
              .lineToY(55)
              .setTangent(Math.toRadians(0))
              .lineToX(32)
              .strafeTo(new Vector2d(44.5, 30))
              .turn(Math.toRadians(180))
              .lineToX(47.5)
              .waitSeconds(3)
              .build());

      myBotSam.runAction(myBotSam.getDrive().actionBuilder(new Pose2d(-36, -55, Math.toRadians(270)))
              .lineToY(-34)
              .lineToY(-40)
              .splineTo(new Vector2d(-57, -35), Math.toRadians(180))
              .waitSeconds(3)
              .lineToX(-52)
              .splineToConstantHeading(new Vector2d(-35 ,-57), Math.toRadians(0))
              .lineToX(20)
              .setTangent(0)
              .splineToConstantHeading(new Vector2d(50 ,-35), Math.toRadians(0))
              .waitSeconds(3)
              .build());


       RoadRunnerBotEntity testPath1 = new DefaultBotBuilder(meepMeep)
           // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
           .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
           .build();

       testPath1.runAction(myBotSam.getDrive().actionBuilder(new Pose2d(0,-30, Math.toRadians(90)))
               .waitSeconds(1)
               .setReversed(true)
               //.setTangent(Math.toRadians(240))
           .splineToSplineHeading(new Pose2d(45, -45, Math.toRadians(270)), Math.toRadians(270))//back away from the submersible
           .build());

       RoadRunnerBotEntity testPath2 = new DefaultBotBuilder(meepMeep)
           // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
           .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
           .build();

       testPath2.runAction(myBotSam.getDrive().actionBuilder(new Pose2d(0,-30, Math.toRadians(90)))
           .waitSeconds(1)
           .setReversed(true)
           //.setTangent(Math.toRadians(240))
           .splineToSplineHeading(new Pose2d(20, -35, Math.toRadians(270)), Math.toRadians(0))//back away from the submersible
           .splineToConstantHeading(new Vector2d(50, -48), Math.toRadians(270)) //go to pick up the second specimen from the wall
           .build());






       RoadRunnerBotEntity myBotRedHang = new DefaultBotBuilder(meepMeep)
           // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
           .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
           .build();

       myBotRedHang.runAction(myBotSam.getDrive().actionBuilder(new Pose2d(0,-55, Math.toRadians(90)))
//            .afterTime(0,claw.action(1)) //close claw
//                .afterTime(0, elbow.action(-1165, 0.5))
//                .afterTime(0, slide.action(-1100, 0.5))
                .waitSeconds(1)
                .splineToConstantHeading(new Vector2d( 10,-29), Math.toRadians(90)) //move to chamber
//                .afterTime(0, claw.action(0))
//                .afterTime(0.3, elbow.action(0, 0.5))
//                .afterTime(0, slide.action(0, 0.5))
                .waitSeconds(.1)
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(30, -30, Math.toRadians(270)), Math.toRadians(360))//back away from the submersible
                .splineToConstantHeading(new Vector2d(36, -12), Math.toRadians(90))// move around the leg of the submersible
                .splineToConstantHeading(new Vector2d(48, -12), Math.toRadians(0))// move to centered on teh left sample
                .splineToConstantHeading(new Vector2d(48, -40), Math.toRadians(270), new TranslationalVelConstraint(25)) //slow down for pickup
                .splineToConstantHeading(new Vector2d(48, -10), Math.toRadians(90), new TranslationalVelConstraint(25)) //go fetch center sample
                .splineToConstantHeading(new Vector2d(60, -10), Math.toRadians(270), new TranslationalVelConstraint(25)) //move centered to center sample
//                .afterTime(0, elbow.action( -300, .75))
//                .afterTime(0, slide.action( -400, 0.75)) //raise and extend the arm to the position of the specimen on the wall
                .splineToConstantHeading(new Vector2d(50, -38), Math.toRadians(270), new TranslationalVelConstraint(25)) //push center sample
                .splineToConstantHeading(new Vector2d(50, -48), Math.toRadians(270), new TranslationalVelConstraint(20)) //slow down for sample drop off and run into the specimen on the wall
//                .afterTime(0, claw.action(1))
//                .waitSeconds(1)
//                .afterTime(0, elbow.action( -1165, 0.5))
//                .waitSeconds(1)
//                .afterTime(.5, slide.action( -1100, 0.5)) //raise and extend the arm to the height of the upper bar on the submersible
                .setReversed(true)
                .splineToSplineHeading(new Pose2d(10,-27, Math.toRadians(90)), Math.toRadians(90)) //move to chamber
//                .afterTime(0, claw.action(0)) //open claw to release the specimen that is on the bar
//                .waitSeconds(1)
               .setReversed(true)
           .setTangent(Math.toRadians(0))
           .splineToSplineHeading(new Pose2d(30, -30, Math.toRadians(270)), Math.toRadians(360))//back away from the submersible
           //.splineToSplineHeading(new Pose2d(20, -35, Math.toRadians(270)), Math.toRadians(270))//back away from the submersible
           .splineToConstantHeading(new Vector2d(50, -48), Math.toRadians(270)) //go to pick up the second specimen from the wall
//           .afterTime(0, elbow.action( -300, .75))
//                .afterTime(0, slide.action( -400, 0.75)) //raise and extend the arm to the position of the specimen on the wall
           .build());


       RoadRunnerBotEntity chamberCycle = new DefaultBotBuilder(meepMeep)
           // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
           .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
           .build();

       chamberCycle.runAction(myBotSam.getDrive().actionBuilder(new Pose2d(10,-55, Math.toRadians(90)))
           .splineToConstantHeading(new Vector2d( 10,-29), Math.toRadians(90)) //move to chamber
           .setReversed(true)
               //.setTangent(Math.toRadians(200))
               .splineToConstantHeading(new Vector2d( 10,-32), Math.toRadians(270)) //move to chamber
           .setReversed(true)
               .splineToSplineHeading(new Pose2d(40, -45, Math.toRadians(270)),Math.toRadians(0))
                   .setReversed(true)
         //      .setTangent(Math.toRadians(0))
               //.splineToConstantHeading(new Vector2d( 50,-55), Math.toRadians(270))
           .splineToSplineHeading(new Pose2d(50, -55, Math.toRadians(270)),Math.toRadians(0))

                   //.splineTo(new Vector2d( 40,-55), Math.toRadians(270))
               //.splineTo(new Vector2d( 30,-40),Math.toRadians(0))

               //.setReversed(true)
           //.splineTo(new Vector2d( 40,-60),Math.toRadians(270))
           .build());

      RoadRunnerBotEntity chamberCycle2 = new DefaultBotBuilder(meepMeep)
            // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
            .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
            .build();

      chamberCycle2.runAction(myBotSam.getDrive().actionBuilder(new Pose2d(10,-55, Math.toRadians(90)))
            .waitSeconds(2)
            .splineToConstantHeading(new Vector2d( 10,-29), Math.toRadians(90)) //move to chamber
            .setTangent(Math.toRadians(0))
            .setReversed(true)
            .strafeToLinearHeading(new Vector2d(40, -55), Math.toRadians(270), new TranslationalVelConstraint(100))
            .splineToConstantHeading(new Vector2d( 40,-58), Math.toRadians(270))
            //.waitSeconds(2)
            .strafeToLinearHeading(new Vector2d(0, -29),Math.toRadians(89.99))
            .build());







            RoadRunnerBotEntity myYellowDrop = new DefaultBotBuilder(meepMeep)
           // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
           .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
           .build();




       myYellowDrop.runAction(myBotSam.getDrive().actionBuilder(new Pose2d(-40, -55, Math.toRadians(90)))
           .splineToSplineHeading(new Pose2d(-50, -50, Math.toRadians(225)), Math.toRadians(180))
           .waitSeconds(1)//extend the arm to drop yellow sample in the bucket
           .setReversed(true) //lower the arm to the height that will pick up the sample off the floor
           .splineToSplineHeading(new Pose2d(-45, -50, Math.toRadians(90)), Math.toRadians(0)) //go to the position of the right side yellow sample on the floor
           .waitSeconds(1) //extend the arm the pick the yellow sample off of the floor and lift the arm to bucket height
           .splineToSplineHeading(new Pose2d(-50, -50, Math.toRadians(225)),  Math.toRadians(225)) //go to bucket
           .waitSeconds(1) //drop the sample in the bucket
           .setReversed(true)
           .splineToSplineHeading(new Pose2d(-58, -50, Math.toRadians(90)), Math.toRadians(180)) //go to the position of the center yellow sample on the floor
           .waitSeconds(1) //pick up center sample and lift arm
           .setReversed(true)
           .splineToSplineHeading(new Pose2d(-50, -50, Math.toRadians(225)), Math.toRadians(315)) //go to bucket
           .waitSeconds(1) //drop the sample in the bucket
           .splineToSplineHeading(new Pose2d(-58, -50, Math.toRadians(100)), Math.toRadians(150)) //go to the left yellow sample
           .waitSeconds(1) //pick up the left sample and lift arm
           .splineToSplineHeading(new Pose2d(-50, -50, Math.toRadians(225)), Math.toRadians(0)) //go to the bucket

           .waitSeconds(1) //drop the sample
           .setReversed(true)
           .splineToSplineHeading(new Pose2d(-40, -30, Math.toRadians(270)), Math.toRadians(90)) //parking approach
           .splineToConstantHeading(new Vector2d(-20, -12), Math.toRadians(0)) //park

           .build());




       RoadRunnerBotEntity myBotYellowMesloh1 = new DefaultBotBuilder(meepMeep)
           // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
           .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
           .build();

       myBotYellowMesloh1.runAction(myBotSam.getDrive().actionBuilder(new Pose2d(-40,-60, Math.toRadians(180)))
               .waitSeconds(4)
               //extend arm
               //raise arm
               .strafeTo(new Vector2d(-55,-60))
           .waitSeconds(1)
               .setTangent(Math.toRadians(90))
               .splineToSplineHeading(new Pose2d(-50,-30,Math.toRadians(90)),Math.toRadians(90))
               //.splineTo(new Vector2d(-48,-30),Math.toRadians(90))
                   //.strafeTo(new Vector2d(-30,30))
               //drop sample
           .waitSeconds(3)
               .build());


      meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
              .setDarkMode(true)
              .setBackgroundAlpha(0.95f)
          //.addEntity(chamberCycle)
  //          .addEntity(chamberCycle2)
              .addEntity(myBotRedHang)
              //.addEntity(myYellowDrop)
//          .addEntity(testPath2)
              .start();
   }
}