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
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

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
            mechanicalDriveBase.gamepadController(gamepad1);

            if (gamepad1.a)
            {
                telemetry.addData("A HIT", "");
                telemetry.addData("Values, ","z is: " + sampleDetection.getZ() + "\ny is: " + sampleDetection.getY() + "\nx is: " + sampleDetection.getX());
                sampleDetection.contours();
                if (sampleDetection.getZ() < 110 && sampleDetection.getZ() > 48)
                {
                    telemetry.addData("z is > 48", "");
                    linearSlide.encoderControl((int) (-1 * motorTicksConversion.linearSlideInCM() * deltaChange.armLength(sampleDetection.getZ(), 1) - 48), 0.7);
                }
            }
            if (gamepad1.y)
            {
                slideTheta.encoderControl((int) (motorTicksConversion.ThetaInDegrees() * (Math.acos(sampleDetection.getZ()/deltaChange.armLength(sampleDetection.getZ(), 1)))),0.3);
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
