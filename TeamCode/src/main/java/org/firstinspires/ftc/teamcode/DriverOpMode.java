package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Driver Controlled (Robot Centric) 2021", group = "Driver")
public class DriverOpMode extends LinearOpMode {

    private double getGamepadMoveMagnitude(Gamepad gamepad) {
        return -Math.hypot(gamepad.left_stick_x, gamepad.left_stick_y) * 1.5;
    }

    private double getGamepadTurnMagnitude(Gamepad gamepad) {
        return gamepad.right_stick_x;
    }

    private double getGamepadMoveAngle(Gamepad gamepad) {
        return Math.atan2(gamepad.left_stick_y, gamepad.left_stick_x);
    }

    @Override
    public void runOpMode() throws InterruptedException {

        ElapsedTime time = new ElapsedTime();
        RoboHelper robot = new RoboHelper(hardwareMap, time);

        waitForStart();

        Debouncer debouncer = new Debouncer(.2);
        Debouncer psxDebouncer = new Debouncer(.2);
        Debouncer speedDebouncer = new Debouncer(.4);
        Debouncer psxSpeedDebouncer = new Debouncer(.4);


        while (opModeIsActive()) {
            robot.handleGamepads(gamepad1, gamepad2);

            if (gamepad1.left_bumper || gamepad2.left_bumper) {
                robot.startSpinnerOther();
            } else {
                robot.stopSpinner();
            }
            if (gamepad1.right_bumper || gamepad2.right_bumper) {
                robot.startSpinner();
            } else {
                robot.stopSpinner();
            }

            if (debouncer.isPressed(gamepad1.a) || psxDebouncer.isPressed(gamepad2.cross) || psxDebouncer.isPressed(gamepad1.circle)) {
                robot.gripperToggle();
            }

            if (gamepad1.dpad_up || gamepad2.dpad_up) {
                robot.raiseArm();
            } else {
                robot.stopArm();
            }
            if (gamepad1.dpad_down || gamepad2.dpad_down) {
                robot.lowerArm();
            } else {
                robot.stopArm();
            }

            if (speedDebouncer.isPressed(gamepad1.y) || psxSpeedDebouncer.isPressed(gamepad2.triangle)) {
                if (robot.speedScale == 1) {
                    robot.speedScale = 0.5;
                } else {
                    robot.speedScale = 1;
                }
            }

            telemetry.addData("robot.speedScale", robot.speedScale);

            telemetry.update();
        }
    }
}
