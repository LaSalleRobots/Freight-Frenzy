package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Blue Autonomous", group = "Pre-Programed")
public class BlueAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime runtime = new ElapsedTime();
        RoboHelper robot = new RoboHelper(hardwareMap, runtime);

        waitForStart();

        robot.moveRight().runFor(0.1);
        robot.moveBackwards().runFor(0.2);
        robot.speedScale = 0.05;
        for (int i = 0; i < 4; i++) {
            robot.moveLeft();
            robot.startSpinner().runFor(3.6);
            robot.stopSpinner().runFor(0.7);
        }
        robot.speedScale = .4;
        robot.moveRight().runFor(.2);

    }

}
