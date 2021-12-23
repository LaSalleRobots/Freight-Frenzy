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

            if (debouncer.isPressed(gamepad1.a) || psxDebouncer.isPressed(gamepad2.cross)) {
                robot.arm.gripper.toggle();
            }

            if (gamepad1.dpad_up || gamepad2.dpad_up) {
                robot.arm.raise();
            }
            if (gamepad1.dpad_down || gamepad2.dpad_down) {
                robot.arm.lower();
            }

            telemetry.update();
        }
    }
}
