package org.firstinspires.ftc.teamcode.algirithums.samplePickup;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.controller.DriveController;
import org.firstinspires.ftc.teamcode.prototype.Elbow;
import org.firstinspires.ftc.teamcode.prototype.Slide;
import org.firstinspires.ftc.teamcode.vision.SampleDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import java.util.ArrayList;


@Autonomous(name = "Failing")
public class DumbyTestProgram extends LinearOpMode
{
    DriveController drive;
    SampleDetection sampleDetection;
    MotorTicksConversion motorTicksConversion;
    DeltaChange deltaChange;
    Slide slide;
    Elbow elbow;

    @Override
    public void runOpMode() throws InterruptedException
    {
        drive = new DriveController(hardwareMap);
        sampleDetection = new SampleDetection(telemetry);
        slide = new Slide(this);
        elbow = new Elbow(this);
        motorTicksConversion = new MotorTicksConversion();
        deltaChange = new DeltaChange();
        initCamera();

        waitForStart();

        while (opModeIsActive())
        {
            //if (1==1)
            if (gamepad1.a)
            {
                ArrayList<ArrayList<Double>> object_distances = sampleDetection.object_distances();
                for (int i = 0; i < object_distances.size(); i++)
                {
                    telemetry.addData("x", Math.round(object_distances.get(i).get(0)));
                    telemetry.addData("y", Math.round(object_distances.get(i).get(1)));
                    telemetry.addData("z", Math.round(object_distances.get(i).get(2)));
                    telemetry.addData("", "");
                }
                double object_x = object_distances.get(0).get(0);
                double object_y = object_distances.get(0).get(1);
                double object_z = object_distances.get(0).get(2)-20;
                if (object_z < 0)
                {
                    continue;
                }
                telemetry.addData("nearest X", Math.round(object_x));
                telemetry.addData("nearest Y", Math.round(object_y));
                telemetry.addData("nearest Z", Math.round(object_z));
                telemetry.addData("motor", (int) Math.round((1 * motorTicksConversion.linearSlideInCM() * deltaChange.armLength(object_z, 1))));
                if (object_z < 0)
                {
                    telemetry.update();
                    continue;
                }
                slide.encoderControl((int) Math.round((1 * motorTicksConversion.linearSlideInCM() * deltaChange.armLength(object_z, 1))), 0.5);
                /*
            }

            if (gamepad1.y)
            {
                double[] nearest_object = sampleDetection.get_nearest_object();
                telemetry.addData("nearest object", nearest_object);
                double object_z = nearest_object[2];
                slideTheta.encoderControl((int) Math.round((motorTicksConversion.ThetaInDegrees() * (Math.acos(object_z/deltaChange.armLength(object_z, 1))))),0.3);
*/
            }
            telemetry.update();
        }
    }

    private void initCamera()
    {
        //https://github.com/OpenFTC/EasyOpenCV/blob/master/doc/user_docs/camera_initialization_overview.md
        String camera_name = "Webcam 1";
        //OpenCvCamera camera = OpenCvCameraFactory.getInstance().createInternalCamera2(OpenCvInternalCamera2.CameraDirection.BACK);
        WebcamName webcamName = hardwareMap.get(WebcamName.class, camera_name);
        OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName);
        sampleDetection = new SampleDetection(telemetry);
        FtcDashboard.getInstance().startCameraStream(camera,0);
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.setViewportRenderer(OpenCvCamera.ViewportRenderer.GPU_ACCELERATED);
                camera.startStreaming(320, 180, OpenCvCameraRotation.UPRIGHT);
                camera.setPipeline(sampleDetection);
            }
            @Override
            public void onError(int errorCode)
            {
                telemetry.addData("Camera Failed","");
                telemetry.update();
            }
        });
    }
}
