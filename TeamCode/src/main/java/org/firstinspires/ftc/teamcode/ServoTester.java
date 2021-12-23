package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
public class ServoTester extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Servo test = hardwareMap.get(Servo.class, "servotest");
        Debouncer debouncer = new Debouncer(.2);
        int pos = 0;

        waitForStart();

        while (true) {
            if (debouncer.isPressed(gamepad1.a)) {
                if (pos == 0) {
                    pos = 180;
                } else {
                    pos = 0;
                }
                test.setPosition(pos);
            }

        }
    }
}
