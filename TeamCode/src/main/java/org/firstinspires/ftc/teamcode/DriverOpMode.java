package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Driver Controlled (Robot Centric) 2021", group = "Driver")
public class DriverOpMode extends LinearOpMode {



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
        driver.left_stick.bind((Gamepad.ThumbStick t)->{
            robot.handleGamepads(gamepad1, gamepad2);
        });
        driver.right_stick.bind((Gamepad.ThumbStick t)->{
            robot.handleGamepads(gamepad1, gamepad2);
        });
        driver.start();
        Gamepad copilot = new Gamepad(gamepad2);

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
        copilot.a.bind((Boolean x)->{
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
            telemetry.update();
        }
    }
}
