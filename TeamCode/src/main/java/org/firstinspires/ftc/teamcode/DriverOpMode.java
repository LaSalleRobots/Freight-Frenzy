package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

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
        DcMotor fL = hardwareMap.get(DcMotor.class, "fL"); // Front Left
        DcMotor bL = hardwareMap.get(DcMotor.class, "bL"); // Back  Left
        DcMotor fR = hardwareMap.get(DcMotor.class, "fR"); // Front Right
        DcMotor bR = hardwareMap.get(DcMotor.class, "bR"); // Back  Right


        DcMotor carousel = hardwareMap.get(DcMotor.class, "spinnyMaGig"); // carousel




        fL.setDirection(DcMotorSimple.Direction.REVERSE);
        bL.setDirection(DcMotorSimple.Direction.REVERSE);
        fR.setDirection(DcMotorSimple.Direction.FORWARD);
        bR.setDirection(DcMotorSimple.Direction.FORWARD);

        double flP = 0;
        double blP = 0;
        double frP = 0;
        double brP = 0;

        waitForStart();

        while (opModeIsActive()) {
            // Mechanum movement input code
            flP =
                    getGamepadMoveMagnitude(gamepad1)
                            * Math.sin(getGamepadMoveAngle(gamepad1) + (Math.PI / 4))
                            + getGamepadTurnMagnitude(gamepad1);
            fL.setPower(flP);
            blP =
                    getGamepadMoveMagnitude(gamepad1)
                            * Math.sin(getGamepadMoveAngle(gamepad1) - (Math.PI / 4))
                            + getGamepadTurnMagnitude(gamepad1);
            bL.setPower(blP);
            frP =
                    getGamepadMoveMagnitude(gamepad1)
                            * Math.sin(getGamepadMoveAngle(gamepad1) + (Math.PI / 4))
                            - getGamepadTurnMagnitude(gamepad1);
            fR.setPower(frP);
            brP =
                    getGamepadMoveMagnitude(gamepad1)
                            * Math.sin(getGamepadMoveAngle(gamepad1) - (Math.PI / 4))
                            - getGamepadTurnMagnitude(gamepad1);
            bR.setPower(brP);

            if (gamepad1.left_bumper) {
                carousel.setPower(-1);
            } else {
                carousel.setPower(0);
            }


            // Mechanum movement telemetry
            telemetry.addData("Front Left", flP);
            telemetry.addData("Front Right", frP);
            telemetry.addData("Back Left", blP);
            telemetry.addData("Back Right", brP);

            telemetry.update();
        }
    }
}
