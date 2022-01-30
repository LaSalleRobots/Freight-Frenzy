package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Full Auto Red (left-delayed): CAM", group = "AI")
public class AIRedLeftDelayedAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime runtime = new ElapsedTime();
        RoboHelper robot = new RoboHelper(hardwareMap, runtime);
        robot.drive.speed = 0.5;

        AprilTagDetectionPipeline aprilTagDetectionPipeline = new AprilTagDetectionPipeline();
        OpenCVPipelineRunner vision = new OpenCVPipelineRunner(hardwareMap);
        vision.setPipeline(aprilTagDetectionPipeline);

        waitForStart();
        robot.arm.gripper.close();
        robot.sleep(3+6.5);
        AprilTagDetectionPipeline.Position position = aprilTagDetectionPipeline.getPosition();

        if (position == AprilTagDetectionPipeline.Position.Left) {
            robot.arm.setPosition(robot.arm.BOTTOM_LEVEL - 5); // bottom level
        } else if (position == AprilTagDetectionPipeline.Position.Center) {
            robot.arm.setPosition(robot.arm.MIDDLE_LEVEL - 5); // middle level
        } else {
            robot.arm.setPosition(robot.arm.TOP_LEVEL + 10); // top level
        }

        robot.drive.right().goFor(1.5);
        robot.sleep(.2);
        robot.drive.forward().goFor(1.1);
        robot.sleep(.2);

        robot.arm.gripper.open();
        robot.drive.backward().goFor(1.1);
        robot.sleep(.2);
        robot.drive.rotateRight().goFor(.55);
        robot.drive.backward().goFor(2.5);
        robot.drive.left().goFor(1);
        robot.drive.backward().goFor(1);
        {
            robot.drive.startSlowMode(.4);
            robot.drive.right().goFor(1.5);
            robot.drive.endSlowMode();
        }
        robot.startSpinner(true);
        robot.sleep(3);
        robot.stopSpinner();
        robot.drive.left().goFor(1.5);
        robot.arm.setPosition(0);

    }
}
