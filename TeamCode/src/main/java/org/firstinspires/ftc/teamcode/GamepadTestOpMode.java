package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "GamePad Testing", group = "Driver")
public class GamePadTestOpMode extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        ElapsedTime time = new ElapsedTime();
        RoboHelper robot = new RoboHelper(hardwareMap, time);

        waitForStart();

        Gamepad driver = new Gamepad(gamepad1);

        driver.left_bumper.bind((Boolean x)->{
            if (x) {
                robot.startSpinnerOther();
            } else {
                robot.stopSpinner();
            }
        });
        driver.right_bumper.bind((Boolean x)->{
            if (x) {
                robot.startSpinner();
            } else {
                robot.stopSpinner();
            }
        });
        driver.left_stick.bind((ThumbStick t)->{
            robot.handleGamepads(gamepad1, gamepad2);
        });
        driver.right_stick.bind((ThumbStick t)->{
            robot.handleGamepads(gamepad1, gamepad2);
        });
        PS4Gamepad copilot = new PS4Gamepad(gamepad2);

        copilot.left_bumper.bind((Boolean x)->{
            if (x) {
                robot.startSpinnerOther();
            } else {
                robot.stopSpinner();
            }
        });
        copilot.right_bumper.bind((Boolean x)->{
            if (x) {
                robot.startSpinner();
            } else {
                robot.stopSpinner();
            }
        });
        copilot.cross.bind((Boolean x)->{
            robot.arm.gripper.toggle();
        });
        copilot.dpad.up.bind((Boolean x)-> {
            robot.arm.raise();
        });
        copilot.dpad.down.bind((Boolean x)-> {
            robot.arm.lower();
        });
        copilot.start();

        while (opModeIsActive()) {
            driver.tick();
            telemetry.update();
        }
        copilot.thread.interrupt();

    }

}
