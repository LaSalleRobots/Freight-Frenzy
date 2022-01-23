package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Disabled
public class AIRedLeftAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime runtime = new ElapsedTime();
        RoboHelper robot = new RoboHelper(hardwareMap, runtime);

        AprilTagDetectionPipeline aprilTagDetectionPipeline = new AprilTagDetectionPipeline();
        OpenCVPipelineRunner vision = new OpenCVPipelineRunner(hardwareMap);
        vision.setPipeline(aprilTagDetectionPipeline);
        vision.start(); // lol we can peep here.

        waitForStart();
        AprilTagDetectionPipeline.Position position = aprilTagDetectionPipeline.getPosition();
        telemetry.addData("Predicted Position:", position);
        telemetry.update();
        vision.close();
        robot.drive.right().goFor(.5); // get to the alliance shipping hub
        // during our approach lets make the arm go the the correct level
        switch (position) {
            case Left:
                robot.arm.setPositionAsync(20); // bottom level
            case Center:
                robot.arm.setPositionAsync(60); // middle level
            case Right:
                robot.arm.setPositionAsync(90); // top level
        }
        // head to the shipping hub
        robot.drive.forward().goFor(1);
        // now that we are there lets drop the element
        robot.arm.gripper.open();
        // viola +(20pts) correct level AI bonus

        robot.drive.backward().goFor(.75); // align ourselves for the carousel
        robot.drive.left().goFor(3); // head over to the carousel
        robot.startSpinner().runFor(1); // do a duck!
        // +(10pts) delivered duck
        robot.drive.right().goFor(1); // prep for a wall slide
        robot.drive.backward().goFor(.25); // wall SLAM!
        robot.drive.right().goFor(6); // park in the warehouse
        // +(10pts) parking

    }
}
