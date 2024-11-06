package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.AngularVelConstraint;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Pose2dDual;
import com.acmerobotics.roadrunner.ProfileAccelConstraint;
import com.acmerobotics.roadrunner.TranslationalVelConstraint;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

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

      RoadRunnerBotEntity myBotDaniel = new DefaultBotBuilder(meepMeep)
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

      myBotDaniel.runAction(myBotDaniel.getDrive().actionBuilder(new Pose2d(0,-55, Math.toRadians(90)))
              .splineToConstantHeading(new Vector2d(a, b), Math.toRadians(90))
              .waitSeconds(1) //space for program to hook specimen on bar
              .setReversed(true)
              .splineToSplineHeading(new Pose2d(0, -50, Math.toRadians(90)), Math.toRadians(270))
          .setTangent(Math.toRadians(360))
          .setReversed(false)
          .splineToSplineHeading(new Pose2d(30, -36, Math.toRadians(360)), Math.toRadians(360))
          .setTangent(Math.toRadians(45))
          .splineToSplineHeading(new Pose2d(32, -12,Math.toRadians(315)), Math.toRadians(45))
          .splineToSplineHeading(new Pose2d( x, y, Math.toRadians(270)), Math.toRadians(270))
//              .setTangent(Math.toRadians(360))
//              .splineToSplineHeading(new Pose2d(30, -36, Math.toRadians(360)), Math.toRadians(360))
//                  .setTangent(Math.toRadians(45))
//                  .splineToSplineHeading(new Pose2d(38, -20,Math.toRadians(315)), Math.toRadians(45))
//                  .splineToSplineHeading(new Pose2d( x, y, Math.toRadians(270)), Math.toRadians(270))
//              .setReversed(true)
//              .setTangent(Math.toRadians(90))
//              .splineToSplineHeading(new Pose2d(42, -10, Math.toRadians(270)), Math.toRadians(90))
//              .splineToConstantHeading(new Vector2d(46, -20), Math.toRadians(270))
//              .splineToConstantHeading(new Vector2d(x, y), Math.toRadians(270))
//              .waitSeconds(1) //space for program that obtains specimen
//              .splineToConstantHeading(new Vector2d(x, y+3), Math.toRadians(90))
//              .splineToSplineHeading(new Pose2d(a+1, b, Math.toRadians(90)), Math.toRadians(90)) //end of first lap

//              .waitSeconds(1) //space for program to hook specimen on bar
//              .splineToSplineHeading(new Pose2d(30, -40, Math.toRadians(270)), Math.toRadians(360))
//              .splineToSplineHeading(new Pose2d(54, -10, Math.toRadians(270)), Math.toRadians(360))
//              .splineToConstantHeading(new Vector2d(56, -20), Math.toRadians(270))
//              .splineToConstantHeading(new Vector2d(x, y), Math.toRadians(270))
//              .waitSeconds(1) //space for program that obtains specimen
//              .splineToConstantHeading(new Vector2d(x, y+3), Math.toRadians(90))
//              .splineToSplineHeading(new Pose2d(a+2, b, Math.toRadians(90)), Math.toRadians(90)) //end of second lap
//
//              .waitSeconds(1) //space for program to hook specimen on bar
//              .splineToSplineHeading(new Pose2d(30, -40, Math.toRadians(270)), Math.toRadians(360))
//              .splineToSplineHeading(new Pose2d(58, -10, Math.toRadians(270)), Math.toRadians(360))
//              .splineToConstantHeading(new Vector2d(61, -20), Math.toRadians(270))
//              .splineToConstantHeading(new Vector2d(61, y), Math.toRadians(270)) //this line may not be necessary if robot can catch the sample on its side
//              .splineToConstantHeading(new Vector2d(x,y), Math.toRadians(180))
//              .waitSeconds(1) //space for program that obtains specimen
//              .splineToConstantHeading(new Vector2d(x, y+3), Math.toRadians(90))
//              .splineToSplineHeading(new Pose2d(a+2, b, Math.toRadians(90)), Math.toRadians(90)) //end of second lap
//              .waitSeconds(1) //space for program to hook specimen on bar
//              .splineToConstantHeading(new Vector2d(53, -58), Math.toRadians(270)) //parking
              .build());


       RoadRunnerBotEntity myBot2ChamberMesloh1 = new DefaultBotBuilder(meepMeep)
           // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
           .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
           .build();

       myBot2ChamberMesloh1.runAction(myBotSam.getDrive().actionBuilder(new Pose2d(0,-55, Math.toRadians(90)))
//           .afterTime(0,claw.action(1)) //close claw
//           .afterTime(0, protoSlideTheta.action( -1165, 0.5))
//           .afterTime(0, protoLinearSlide.action(-1100, 0.5))
           .waitSeconds(1)
           .splineToConstantHeading(new Vector2d( 0,-29), Math.toRadians(90)) //move to chamber
//           .afterTime(0, claw.action(0)) //begins when the action after it begins
           .waitSeconds(.1)
           .setTangent(Math.toRadians(0))
           .splineToSplineHeading(new Pose2d(19, -30, Math.toRadians(180)), Math.toRadians(0))  //head right while rotating
//           .afterTime(0, protoSlideTheta.action( 0, 0.5))
//           .afterTime(0, protoLinearSlide.action(0, 0.5))
           //.waitSeconds(.1)
           .splineToSplineHeading(new Pose2d(36, -30, Math.toRadians(270)), Math.toRadians(360))//.setReversed(true)
           //.setTangent(Math.toRadians(90))
           .splineToConstantHeading(new Vector2d(42, -12), Math.toRadians(90))//.setReversed(true)
        //   .splineToConstantHeading(new Vector2d( 48, -10), Math.toRadians(90))
//           .afterTime(0, protoSlideTheta.action( -300, .75))
//           .afterTime(0, protoLinearSlide.action( -300, 0.75))
        //   .waitSeconds(.1)
          // .setTangent(Math.toRadians(270))
           .splineToConstantHeading(new Vector2d(46, -40), Math.toRadians(270), new TranslationalVelConstraint(100))  //push sample
             .splineToConstantHeading(new Vector2d(46, -50), Math.toRadians(270), new TranslationalVelConstraint(10)) //slow down for pickup
//           .afterTime(0, claw.action(1))
//           .afterTime(0, protoSlideTheta.action( -1165, 0.5))
//                .afterTime(0, protoLinearSlide.action( -1100, 0.5))
           .waitSeconds(.1)
           .setTangent(Math.toRadians(45))
//                .setReversed(true)
//                .splineToSplineHeading(new Pose2d(0, -51, Math.toRadians(180)), Math.toRadians(180))
                .setReversed(true)
           .splineToSplineHeading(new Pose2d(0,-29, Math.toRadians(90)), Math.toRadians(90)) //move to chamber
           //  .strafeToLinearHeading(new Vector2d(0, -27), Math.toRadians(90.1)) //alternate to chamber
//           .afterTime(0, claw.action(0))
//           .afterTime(0, protoSlideTheta.action( 0, 0.5))
//           .afterTime(0, protoLinearSlide.action(0, 0.5))
           .waitSeconds(.1)
           .build());


//
//                       .afterTime(0,claw.action(1)) //close claw
//           .afterTime(0, protoSlideTheta.action( -1165, 0.5))
//           .afterTime(0, protoLinearSlide.action(-1100, 0.5))
//           .waitSeconds(1)
//           .splineToConstantHeading(new Vector2d( 0,-29), Math.toRadians(90)) //move to chamber
//           .afterTime(0, claw.action(0)) //begins when the action after it begins
//           .waitSeconds(.1)
//           .setTangent(Math.toRadians(360))
//           .splineToSplineHeading(new Pose2d(19, -30, Math.toRadians(180)), Math.toRadians(360))
//           .afterTime(0, protoSlideTheta.action( 0, 0.5))
//           .afterTime(0, protoLinearSlide.action(0, 0.5))
//           .waitSeconds(.1)
//           .splineToSplineHeading(new Pose2d(36, -30, Math.toRadians(270)), Math.toRadians(360))
//           .setTangent(Math.toRadians(90))
//           .splineToConstantHeading(new Vector2d(36, -12), Math.toRadians(90))
//           .splineToConstantHeading(new Vector2d( 48, -8), Math.toRadians(90))
//           .afterTime(0, protoSlideTheta.action( -300, .75))
//           .afterTime(0, protoLinearSlide.action( -300, 0.75))
//           .waitSeconds(.1)
////                .splineToSplineHeading(new Pose2d(42, -35, Math.toRadians(360)), Math.toRadians(90))
////                .splineToSplineHeading(new Pose2d(40, -12, Math.toRadians(315)), Math.toRadians(360)) //sample floor location
////                .afterTime(0, protoSlideTheta.action( -300, .75))
////                .afterTime(0, protoLinearSlide.action( -300, 0.75))
////                .waitSeconds(.5)
//           .setTangent(Math.toRadians(270))
//           .splineToConstantHeading(new Vector2d(44, -52), Math.toRadians(270))


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


      meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_LIGHT)
              .setDarkMode(true)
              .setBackgroundAlpha(0.95f)
             // .addEntity(myBotDaniel)
              .addEntity(myBot2ChamberMesloh1)
              .start();
   }
}