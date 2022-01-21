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

        robot.drive.forward().goFor(0.7); // drive to the shipping hub
        robot.sleep(0.5); //DEBUG: sleep and kill momentum
        robot.drive.rotateRight().goFor(0.3); // 90 Deg turn TUNE ME
        robot.sleep(0.1); // stop any momentum/velocity
        robot.arm.setPosition(robot.arm.TOP_LEVEL+10);
        robot.sleep(0.1); // carefully get ready
        robot.drive.forward().goFor(0.1); // nudge over to the shipping hub
        robot.arm.gripper.release(); // drop our payload
        robot.drive.backward().goFor(0.6); // back towards the wall by the carousel
        robot.arm.setPositionAsync(0); // we don't need this anymore
        robot.arm.deactivate(); // lets save some power
        robot.drive.right().goFor(1); // head to the carousel
        robot.drive.speed = 0.2;
        robot.drive.backward().goFor(0.2); // back into the carefully
        robot.startSpinner();
    }
}
