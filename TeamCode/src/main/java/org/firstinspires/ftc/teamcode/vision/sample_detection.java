package org.firstinspires.ftc.teamcode.vision;

//import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class sample_detection extends OpenCvPipeline
{
   private Mat srcGray = new Mat();
   private static final int MAX_THRESHOLD = 255;
   private int threshold = 100;
   Telemetry telemetry;
   double x_resolution = 320;
   double y_resolution = 180;
   public Scalar lower_red = new Scalar(0, 171, 75);
   public Scalar lower_blue = new Scalar(0, 0, 140);
   public Scalar lower = new Scalar(0, 171, 75);
   public Scalar upper = new Scalar(255, 255, 255);
   public Scalar blur = new Scalar(1, 1, 0);
   private Mat ycrcbMat       = new Mat();
   private Mat binaryMat      = new Mat();
   private Mat maskedInputMat = new Mat();
   Mat gray = new Mat();

   @Override
   public void init(Mat firstFrame)
   {

   }

   public sample_detection(Telemetry telemetry) {
      this.telemetry = telemetry;
   }
   @Override
   public Mat processFrame(Mat input)
   {
      // Create mask to remove all background noise (depending on our color rgb color bounds)
      Imgproc.cvtColor(input, ycrcbMat, Imgproc.COLOR_RGB2YCrCb);
      Core.inRange(ycrcbMat, lower, upper, binaryMat);
      maskedInputMat.release();
      Core.bitwise_and(input, input, maskedInputMat, binaryMat);

      // Start locating objects
      Imgproc.cvtColor(maskedInputMat, gray, Imgproc.COLOR_BGR2GRAY);
      Imgproc.blur(gray, gray, new Size(blur.val[0], blur.val[1]));
      Mat cannyOutput = new Mat();
      Imgproc.Canny(gray, cannyOutput, threshold, threshold*2);

      // add found edges to an array
      List<MatOfPoint> contours = new ArrayList<>();
      Mat hierarchy = new Mat();
      Imgproc.findContours(cannyOutput, contours, hierarchy, Imgproc.RETR_EXTERNAL, Imgproc.CHAIN_APPROX_SIMPLE);

      // Find the rotated rectangles and ellipses for each contour
      RotatedRect[] minRect = new RotatedRect[contours.size()];
      RotatedRect[] minEllipse = new RotatedRect[contours.size()];
      for (int i = 0; i < contours.size(); i++) {
         minRect[i] = Imgproc.minAreaRect(new MatOfPoint2f(contours.get(i).toArray()));
         minEllipse[i] = new RotatedRect();
         if (contours.get(i).rows() > 5) {
            minEllipse[i] = Imgproc.fitEllipse(new MatOfPoint2f(contours.get(i).toArray()));
         }
      }

      // Draw contours, elipses, and rectangles
      Mat drawing = Mat.zeros(cannyOutput.size(), CvType.CV_8UC3);
      for (int i = 0; i < contours.size(); i++) {
         Scalar color = new Scalar(256, 256, 256);
         // Draw contour
         Imgproc.drawContours(input, contours, i, color);
         // Draw ellipse
         //Imgproc.ellipse(input, minEllipse[i], color, 2);
         // Draw rotated rectangle
         Point[] rectPoints = new Point[4];
         minRect[i].points(rectPoints);
         for (int j = 0; j < 4; j++) {
            Imgproc.line(input, rectPoints[j], rectPoints[(j+1) % 4], lower);
         }
      }
      return input;
   }


}
