package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.exception.RobotCoreException;

import java.util.function.Consumer;

public class Gamepad implements Runnable {
    private com.qualcomm.robotcore.hardware.Gamepad baseGamepad;

    // public API
    public Button a;
    public Button b;
    public Button x;
    public Button y;
    public Button left_bumper;
    public Button right_bumper;
    public DPad dpad;
    public ThumbStick left_stick;
    public ThumbStick right_stick;

    public Gamepad(com.qualcomm.robotcore.hardware.Gamepad gp) {
        baseGamepad = gp; // hopefully copy by reference
    }

    public void start() {
        Thread t = new Thread(this);
        t.start(); // will exec in background
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {

        com.qualcomm.robotcore.hardware.Gamepad oldPad = new com.qualcomm.robotcore.hardware.Gamepad();
        try {oldPad.copy(baseGamepad);} catch (RobotCoreException e) {e.printStackTrace();}
        while (true) {

            this.a.isPressed = baseGamepad.a;
            this.b.isPressed = baseGamepad.b;
            this.x.isPressed = baseGamepad.x;
            this.y.isPressed = baseGamepad.y;

            this.dpad.up.isPressed = baseGamepad.dpad_up;
            this.dpad.down.isPressed = baseGamepad.dpad_down;
            this.dpad.left.isPressed = baseGamepad.dpad_left;
            this.dpad.right.isPressed = baseGamepad.dpad_right;

            this.left_stick.x = baseGamepad.left_stick_x;
            this.left_stick.y = baseGamepad.left_stick_y;
            this.right_stick.x = baseGamepad.right_stick_x;
            this.right_stick.y = baseGamepad.right_stick_y;

            // Notify / call any observers for buttons
            if ((oldPad.a && !baseGamepad.a) || (!oldPad.a && baseGamepad.a)) {
                this.a.call();
            }
            if ((oldPad.b && !baseGamepad.b) || (!oldPad.b && baseGamepad.b)) {
                this.b.call();
            }
            if ((oldPad.x && !baseGamepad.x) || (!oldPad.x && baseGamepad.x)) {
                this.x.call();
            }
            if ((oldPad.y && !baseGamepad.y) || (!oldPad.y && baseGamepad.y)) {
                this.y.call();
            }

            if ((oldPad.left_bumper && !baseGamepad.left_bumper) || (!oldPad.left_bumper && baseGamepad.left_bumper)) {
                this.left_bumper.call();
            }
            if ((oldPad.y && !baseGamepad.y) || (!oldPad.y && baseGamepad.y)) {
                this.left_bumper.call();
            }

            if ((oldPad.dpad_up && !baseGamepad.dpad_up) || (!oldPad.dpad_up && baseGamepad.dpad_up)) {
                this.dpad.up.call();
                this.dpad.call();
            }
            if ((oldPad.dpad_down && !baseGamepad.dpad_down) || (!oldPad.dpad_down && baseGamepad.dpad_down)) {
                this.dpad.down.call();
                this.dpad.call();
            }
            if ((oldPad.dpad_right && !baseGamepad.dpad_right) || (!oldPad.dpad_right && baseGamepad.dpad_right)) {
                this.dpad.right.call();
                this.dpad.call();
            }
            if ((oldPad.dpad_left && !baseGamepad.dpad_left) || (!oldPad.dpad_left && baseGamepad.dpad_left)) {
                this.dpad.left.call();
                this.dpad.call();
            }


            if ((oldPad.left_stick_x != baseGamepad.left_stick_x)) {
                this.left_stick.call();
            }
            if ((oldPad.left_stick_y != baseGamepad.left_stick_y)) {
                this.left_stick.call();
            }
            if ((oldPad.left_stick_button && !baseGamepad.left_stick_button) || (!oldPad.left_stick_button && baseGamepad.left_stick_button)) {
                this.left_stick.press.call();
                this.left_stick.call();
            }

            if ((oldPad.right_stick_x != baseGamepad.right_stick_x)) {
                this.right_stick.call();
            }
            if ((oldPad.right_stick_y != baseGamepad.right_stick_y)) {
                this.right_stick.call();
            }
            if ((oldPad.right_stick_button && !baseGamepad.right_stick_button) || (!oldPad.right_stick_button && baseGamepad.right_stick_button)) {
                this.right_stick.press.call();
                this.right_stick.call();
            }

            try {oldPad.copy(baseGamepad);} catch (RobotCoreException e) {e.printStackTrace();}
            try {sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
        }

    }


    public static class Button {
        public boolean isPressed;

        private Consumer<Boolean> bound;

        public void bind(Consumer<Boolean> func) {
            bound = func;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void call() {
            if (this.bound != null) {
                this.bound.accept(this.isPressed);
            }
        }
    }

    public static class DPad {
        public Button up;
        public Button down;
        public Button left;
        public Button right;

        private Consumer<DPad> bound;

        public void bind(Consumer<DPad> func) {
            bound = func;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void call() {
            if (this.bound != null) {
                this.bound.accept(this);
            }
        }
    }

    public static class ThumbStick {
        public float x;
        public float y;
        public Button press;

        private Consumer<ThumbStick> bound;

        public void bind(Consumer<ThumbStick> func) {
            bound = func;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public void call() {
            if (this.bound != null) {
                this.bound.accept(this);
            }
        }
    }
}
