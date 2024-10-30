package org.firstinspires.ftc.teamcode.algirithums.samplePickup;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.controller.MechanicalDriveBase;
import org.firstinspires.ftc.teamcode.prototype.ProtoLinearSlide;
import org.firstinspires.ftc.teamcode.prototype.ProtoSlideTheta;
import org.firstinspires.ftc.teamcode.vision.SampleDetection;
import org.opencv.core.MatOfPoint;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Autonomous(name = "Failing")
public class DumbyTestProgram extends LinearOpMode
{
    MechanicalDriveBase mechanicalDriveBase;
    SampleDetection sampleDetection;
    MotorTicksConversion motorTicksConversion;
    DeltaChange deltaChange;
    ProtoLinearSlide linearSlide;
    ProtoSlideTheta slideTheta;

    @Override
    public void runOpMode() throws InterruptedException
    {
        mechanicalDriveBase = new MechanicalDriveBase(hardwareMap);
        sampleDetection = new SampleDetection(telemetry);
        linearSlide = new ProtoLinearSlide(this);
        slideTheta = new ProtoSlideTheta(this);
        motorTicksConversion = new MotorTicksConversion();
        deltaChange = new DeltaChange();
        initCamera();

        waitForStart();

        while (opModeIsActive())
        {
            if (1==1)
            {
                ArrayList<ArrayList<Double>> object_distances = sampleDetection.object_distances();
                for (int i = 0; i < object_distances.size(); i++)
                {
                    telemetry.addData("object points", object_distances.get(i));
                }
                /*
                double[] nearest_object = sampleDetection.get_nearest_object();
                telemetry.addData("nearest object", nearest_object);
                double object_x = nearest_object[0];
                double object_y = nearest_object[1];
                double object_z = nearest_object[2];
                telemetry.addData("nearest X", Math.round(object_x));
                telemetry.addData("nearest Y", Math.round(object_y));
                telemetry.addData("nearest Z", Math.round(object_z));

                if (object_z < 110 && object_z > 48)
                {
                    telemetry.addData("z is > 48", "");
                    linearSlide.encoderControl((int) Math.round((-1 * motorTicksConversion.linearSlideInCM() * deltaChange.armLength(object_z, 1) - 48)), 0.7);
                }
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
