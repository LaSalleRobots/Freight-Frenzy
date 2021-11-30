package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "FunctionalityTester" )
public class FunctionalityTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime time = new ElapsedTime();
        RoboHelper robot = new RoboHelper(hardwareMap, time);

        waitForStart();

        Debouncer debouncer = new Debouncer(.25);

        while (opModeIsActive()) {
            if (debouncer.isPressed(gamepad1.a)) {
                robot.gripperToggle();
            }
        }
    }
}
