package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "OpenCV Example", group = "AI/CV Concepts")
public class OpenCV_Example extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        TeamElementTracker teamElementTracker = new TeamElementTracker();
        OpenCVPipelineRunner runner = new OpenCVPipelineRunner(hardwareMap, teamElementTracker);

        runner.start();

        switch (teamElementTracker.getPosition()) {
            case Left:
                

        }

        while (opModeIsActive()) {
            telemetry.addData("FPS", runner.phoneCam.getFps());
            telemetry.addData("Pipeline (ms)", runner.phoneCam.getPipelineTimeMs());
            telemetry.addData("Total Frame time (ms)", runner.phoneCam.getTotalFrameTimeMs());
            telemetry.addData(
                    "W, H (px)",
                    teamElementTracker.bounds.size.width + ", " + teamElementTracker.bounds.size.height);
            telemetry.addData("Position (Left,Center,Right)", teamElementTracker.getPosition());
            telemetry.update();
            sleep(100);
        }
    }
}
