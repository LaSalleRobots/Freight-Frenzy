package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp(name = "Driver Controlled", group = "Driver")
public class DriverOpMode extends LinearOpMode {



    @Override
    public void runOpMode() throws InterruptedException {

        ElapsedTime time = new ElapsedTime();
        RoboHelper robot = new RoboHelper(hardwareMap, time);

        waitForStart();

        Debouncer debouncer = new Debouncer(.2);
        Debouncer psxDebouncer = new Debouncer(.2);

        while (opModeIsActive()) {
            robot.handleGamepads(gamepad1, gamepad2);

            if (gamepad2.x) {
                if (gamepad2.y) {
                    if (gamepad2.left_stick_y >= 0.1) {
                        robot.arm.lowerUnbounded(gamepad2.left_stick_y * 0.1);
                    }
                    if (gamepad2.left_stick_y <= -0.1) {
                        robot.arm.raiseUnbounded(Math.abs(gamepad2.left_stick_y) * 0.1);
                    }
                } else {
                    if (gamepad2.left_stick_y >= 0.1) {
                        robot.arm.lower(gamepad2.left_stick_y * 0.1);
                    }
                    if (gamepad2.left_stick_y <= -0.1) {
                        robot.arm.raise(Math.abs(gamepad2.left_stick_y) * 0.1);
                    }
                }

            } else {
                if (gamepad2.left_stick_y >= 0.1) {
                    robot.arm.lower(Math.pow(gamepad2.left_stick_y, 5));
                }
                if (gamepad2.left_stick_y <= -0.1) {
                    robot.arm.raise(Math.pow(Math.abs(gamepad2.left_stick_y), 5));
                }
            }

            if (gamepad2.back) {
                robot.arm.reInit();
            }

            if (gamepad2.left_bumper) {
                robot.startSpinner();
            } else {
                robot.stopSpinner();
            }
            if (gamepad2.right_bumper) {
                robot.startSpinnerOther();
            } else {
                robot.stopSpinner();
            }

            if (gamepad2.dpad_down) {
                robot.arm.setPositionAsync(robot.arm.GROUND_LEVEL);
            }
            if (gamepad2.dpad_right) {
                robot.arm.setPositionAsync(robot.arm.MIDDLE_LEVEL);
            }
            if (gamepad2.dpad_up) {
                robot.arm.setPosition(robot.arm.TOP_LEVEL);
            }

            if (gamepad1.left_bumper) {
                robot.startSpinnerOther();
            } else {
                robot.stopSpinner();
            }
            if (gamepad1.right_bumper) {
                robot.startSpinner();
            } else {
                robot.stopSpinner();
            }

            if (debouncer.isPressed(gamepad1.a) || psxDebouncer.isPressed(gamepad2.a)) {
                robot.arm.gripper.toggle();
            }
            if (debouncer.isPressed(gamepad1.b)) {
                robot.arm.wrist.setPosition(.5);
            }

            telemetry.addData("Arm (deg)", robot.arm.armPosition);
            telemetry.addData("Arm (deg) (current): ", robot.arm.arm.getCurrentPosition()/12);
            telemetry.addData("Wrist (Deg): ", robot.arm.wrist.getPosition());
            telemetry.update();
        }

    }

}
