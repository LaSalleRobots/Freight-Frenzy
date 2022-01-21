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

        robot.drive.forward().goFor(2);
        robot.arm.gripper.close();
        sleep(5 * 1000);
        robot.drive.rotateRight().goFor(.3); // TUNE ME
        robot.arm.gripper.close();
        sleep(5 * 1000);
        robot.arm.setPosition(robot.arm.TOP_LEVEL+20);
        robot.arm.gripper.close();
        sleep(5 * 1000);
        robot.drive.forward().goFor(.05);
        robot.arm.gripper.open();
        sleep(5 * 1000);

        robot.drive.backward().goFor(3);
        robot.arm.setPositionAsync(0);
        robot.drive.right().goFor(3);

        robot.startSpinner();
    }
}
