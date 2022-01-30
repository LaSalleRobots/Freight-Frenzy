package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.openftc.easyopencv.OpenCvTracker;

@Autonomous(name = "OpenCV Example", group = "AI/CV Concepts")
public class OpenCV_Example extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        BasicTracker teamElementTracker = new BasicTracker();
        OpenCVPipelineRunner runner = new OpenCVPipelineRunner(hardwareMap, teamElementTracker);

        waitForStart();

        while (opModeIsActive()) {
            telemetry.addData("FPS", runner.webcam.getFps());
            telemetry.addData("Pipeline (ms)", runner.webcam.getPipelineTimeMs());
            telemetry.addData("Total Frame time (ms)", runner.webcam.getTotalFrameTimeMs());
            /*telemetry.addData(
                    "W, H (px)",
                    teamElementTracker.bounds.size.width + ", " + teamElementTracker.bounds.size.height);
            telemetry.addData("Position (Left,Center,Right)", teamElementTracker.getPosition());*/
            telemetry.update();
            sleep(100);
        }
    }
}
