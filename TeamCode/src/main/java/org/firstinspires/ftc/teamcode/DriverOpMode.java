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

        Debouncer debouncer = new Debouncer(.2);
        Debouncer psxDebouncer = new Debouncer(.2);

        while (opModeIsActive()) {
            robot.handleGamepads(gamepad1, gamepad2);

            if (gamepad2.left_stick_y >= 0.1) {
                robot.arm.raise(gamepad2.left_stick_y);
            }
            if (gamepad2.left_stick_y <= -0.1) {
                robot.arm.lower(Math.abs(gamepad2.left_stick_y));
            }
            if (gamepad2.left_bumper) {
                robot.arm.setPosition(robot.arm.GROUND_LEVEL);
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

            if (debouncer.isPressed(gamepad1.a) || psxDebouncer.isPressed(gamepad2.cross)) {
                robot.arm.gripper.toggle();
            }
            if (debouncer.isPressed(gamepad1.b)) {
                robot.arm.wrist.setPosition(.5);
            }

            if (gamepad1.dpad_up) {
                robot.arm.raise();
            }
            if (gamepad1.dpad_down) {
                robot.arm.lower();
            }
            telemetry.addData("Arm (deg)", robot.arm.armPosition);
            telemetry.addData("Arm (deg) (current): ", robot.arm.arm.getCurrentPosition()/12);
            telemetry.addData("Wrist (Deg): ", robot.arm.wrist.getPosition());
            telemetry.update();
        }

    }

}
