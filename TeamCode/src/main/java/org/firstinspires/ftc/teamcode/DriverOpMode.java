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

        Debouncer carouselDeboucer = new Debouncer(.1);
        ToggleButton toggle = new ToggleButton(true);
        double rampTime = 0.3;

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
                    robot.arm.lower(Math.pow(gamepad2.left_stick_y, 5)*0.5);
                }
                if (gamepad2.left_stick_y <= -0.1) {
                    robot.arm.raise(Math.pow(Math.abs(gamepad2.left_stick_y), 5)*0.5);
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

            if (carouselDeboucer.isPressed(gamepad1.left_bumper)) {
                robot.startRampProgram(rampTime);
                rampTime+=0.1;
            } else {
                robot.stopSpinner();
                rampTime = 0.3;
            }
            if (gamepad1.right_bumper) {
                robot.startSpinner();
            } else {
                robot.stopSpinner();
            }

            if (toggle.checkButton(gamepad2.a)) {
                robot.arm.gripper.open();
            } else {
                robot.arm.gripper.close();
            }

            telemetry.addData("Arm (deg)", robot.arm.armPosition);
            telemetry.addData("Arm (deg) (current): ", robot.arm.arm.getCurrentPosition()/12);
            telemetry.addData("Wrist (Deg): ", robot.arm.wristLeft.getPosition());
            telemetry.addData("Data", robot.plateSpinner.getPower());
            telemetry.addData("ButtonLocker:", toggle.toString());
            telemetry.update();
        }

    }

}
