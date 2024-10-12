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
import org.opencv.imgproc.Imgproc;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.openftc.easyopencv.OpenCvPipeline;

public class SampleDetection extends OpenCvPipeline
{
   private Mat srcGray = new Mat();
   private static final int MAX_THRESHOLD = 255;
   private int threshold = 100;
   Telemetry telemetry;


   // cam_placement scalar format Scalar(camera height[cm], minimum distance visible from cam[cm], cam-to-arm x_distance[cm], cam-to-arm hinge z_distance[cm])
   public Scalar cam_placement = new Scalar(8*2.54, 12.5, 8.5, 29);
   //public Scalar cam_placement = new Scalar(8, 5.75, 3.7, 2.5);
   double camera_height = cam_placement.val[0];
   double distance_minimum_camera = cam_placement.val[1];
   double camera_x_offset = cam_placement.val[2];
   double camera_z_offset = cam_placement.val[3];
   double range_limiter = 2.5;
   double x_resolution = 320;
   double y_resolution = 180;
   double y_fov = 52.2;
   double x_fov = 82.1;

   double angle_difference = Math.toDegrees(Math.atan(distance_minimum_camera/camera_height));
   double x_degrees_per_pixel = x_fov/x_resolution;
   double y_degrees_per_pixel = y_fov/y_resolution;

   public Scalar lower_red = new Scalar(0, 171, 75);
   public Scalar lower_blue = new Scalar(0, 0, 140);
   public Scalar lower = new Scalar(0, 171, 75);
   public Scalar upper = new Scalar(255, 255, 255);
   public Scalar blur = new Scalar(1, 1, 0, 0);
   private Mat ycrcbMat       = new Mat();
   private Mat binaryMat      = new Mat();
   private Mat maskedInputMat = new Mat();
   private double x_distance = 0;
   private double y_distance = 0;
   private double z_distance = 0;
   Mat gray = new Mat();

   public void reduce_bounding_boxes(Point rectangle_points)
   {

   }

   public double calculate_bounding_box_area(Point[] rectangle_points)
   {
      double a = Math.sqrt(Math.pow((rectangle_points[0].x-rectangle_points[1].x), 2) + Math.pow((rectangle_points[0].y-rectangle_points[1].y), 2));
      double b = Math.sqrt(Math.pow((rectangle_points[1].x-rectangle_points[2].x), 2) + Math.pow((rectangle_points[1].y-rectangle_points[2].y), 2));
      double c = Math.sqrt(Math.pow((rectangle_points[2].x-rectangle_points[3].x), 2) + Math.pow((rectangle_points[2].y-rectangle_points[3].y), 2));
      double d = Math.sqrt(Math.pow((rectangle_points[3].x-rectangle_points[0].x), 2) + Math.pow((rectangle_points[3].y-rectangle_points[0].y), 2));
      return(a*b);
   }

   @Override
   public void init(Mat firstFrame)
   {

   }

   public SampleDetection(Telemetry telemetry)
   {
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
      //telemetry.addData("number of contours", contours.size());
      for (int i = 0; i < contours.size(); i++) {
         Scalar color = new Scalar(256, 256, 256);
         // Draw contour
         Imgproc.drawContours(input, contours, i, color);
         // Draw ellipse
         //Imgproc.ellipse(input, minEllipse[i], color, 2);
         // Draw rotated rectangle
         Point[] rectPoints = new Point[4];
         minRect[i].points(rectPoints);
         double size = calculate_bounding_box_area(rectPoints);
         double y_range_limit = y_resolution/range_limiter;
         if (minRect[i].center.y <= y_range_limit)
         {
            continue;
         }
         if (size < 50)
         {
            continue;
         }

         Point pixel_camera_location = minEllipse[i].center;
         double object_x = pixel_camera_location.x;
         double object_y = y_resolution - pixel_camera_location.y;
         Imgproc.circle(input, pixel_camera_location, i*5, new Scalar(0, 255, 0), 1);
         Imgproc.circle(input, pixel_camera_location, 1, new Scalar(0, 255, 0), 2);
         telemetry.addData("i", i);
         //telemetry.addData("object x", object_x);
         //telemetry.addData("object y", object_y);
         double angle = Math.toRadians(angle_difference + y_degrees_per_pixel * (y_resolution - pixel_camera_location.y));
         //telemetry.addData("y_angle", Math.toDegrees(angle));
         double y_distance = camera_height * Math.tan(angle);

         //telemetry.addData("min_ellipse", minEllipse[i].center);
         telemetry.addData("y_distance", y_distance);
         telemetry.addData("calculated size", calculate_bounding_box_area(rectPoints));
         //telemetry.addData("contour size", Imgproc.contourArea(contours.get(i)));

         for (int j = 0; j < 4; j++)
         {
            Imgproc.line(input, rectPoints[j], rectPoints[(j+1) % 4], new Scalar(50, 50, j*60));
         }
         double center_line = (x_degrees_per_pixel*x_resolution)/2;
         double x_angle = (x_degrees_per_pixel*object_x)-center_line;

         double x_distance = Math.tan(Math.toRadians(x_angle))*y_distance+camera_x_offset;
         this.x_distance = x_distance;
         this.z_distance = y_distance;
         //telemetry.addData("x_angle", x_angle);
         telemetry.addData("x_distance", x_distance);
         //telemetry.addData(" ", " ");
      }
      telemetry.update();
      return input;
   }

   public double x_distance()
   {
      return this.x_distance;
   }
   public double z_distance()
   {
      return this.z_distance;
   }
   public double y_distance()
   {
      return this.y_distance;
   }
// look into errosion to try and remove overlapping contour lines.
}