package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Full Auto Red (Left)", group = "AI")
public class RedLeftAuto extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime runtime = new ElapsedTime();
        RoboHelper robot = new RoboHelper(hardwareMap, runtime);


        waitForStart();

        robot.arm.gripper.close();

        robot.drive.speed = 1;

        robot.drive.forward().goFor(0.7);
        robot.sleep(0.5);
        robot.drive.rotateRight().goFor(0.3); // 90 Deg turn TUNE ME
        robot.sleep(0.1); // stop any momentum/velocity
        robot.arm.setPosition(robot.arm.TOP_LEVEL+10);
        robot.sleep(0.1); // carefully get ready
        robot.drive.forward().goFor(0.1);
        robot.arm.gripper.release();
        robot.drive.backward().goFor(0.7);
        robot.arm.setPositionAsync(0);
        robot.drive.right().goFor(1);

        robot.startSpinner();
    }
}
