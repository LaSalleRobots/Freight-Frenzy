package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Full Auto Blue (right-delayed): CAM", group = "AI")
public class AIBlueRightDelayedAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
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
        robot.drive.left().goFor(1.7);
        robot.sleep(.2);
        robot.drive.rotateLeft().goFor(.05);
        robot.drive.forward().goFor(.8);
        robot.sleep(.2);

        robot.arm.gripper.open();
        robot.drive.backward().goFor(1.5);
        robot.sleep(.2);
        robot.drive.right().goFor(2.4);
        {
            robot.drive.startSlowMode(.4);
            robot.drive.right().goFor(1.5);
            robot.drive.endSlowMode();
        }
        robot.startSpinnerOther(true);
        robot.sleep(3);
        robot.stopSpinner();
        robot.drive.forward().goFor(1.1);
        robot.drive.rotateLeft().goFor(.55);
        robot.drive.backward().goFor(.5);
        robot.arm.gripper.open();
        robot.arm.setPosition(0);

    }
}
