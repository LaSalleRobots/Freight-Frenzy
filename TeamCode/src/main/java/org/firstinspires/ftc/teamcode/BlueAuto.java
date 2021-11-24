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
        robot.speedScale = 0.7;

        waitForStart();

        robot.startSpinner().runFor(5).stopSpinner();
        robot.moveBackwards().runFor(1.5);
        robot.moveLeft().runFor(0.5);

    }

}
