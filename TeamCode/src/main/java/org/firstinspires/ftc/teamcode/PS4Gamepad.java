package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.qualcomm.robotcore.exception.RobotCoreException;

public class PS4Gamepad implements Runnable {
    private com.qualcomm.robotcore.hardware.Gamepad baseGamepad;

    // public API
    public Button cross;
    public Button circle;
    public Button square;
    public Button triangle;
    public Button left_bumper;
    public Button right_bumper;
    public DPad dpad;
    public ThumbStick left_stick;
    public Trigger left_trigger;
    public ThumbStick right_stick;
    public Trigger right_trigger;
    public TouchPad finger1;
    public TouchPad finger2;

    public Thread thread;

    public PS4Gamepad(com.qualcomm.robotcore.hardware.Gamepad gp) {
        baseGamepad = gp; // hopefully copy by reference
        cross = new Button();
        circle = new Button();
        square = new Button();
        triangle = new Button();
        left_bumper = new Button();
        right_bumper = new Button();
        dpad = new DPad();
        left_stick = new ThumbStick();
        right_stick = new ThumbStick();
        left_trigger = new Trigger();
        finger1 = new TouchPad();
        finger2 = new TouchPad();
    }

    public void start() {
        thread = new Thread(this);
        thread.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        com.qualcomm.robotcore.hardware.Gamepad oldPad = new com.qualcomm.robotcore.hardware.Gamepad();
        while (true) {
            this.cross.isPressed = baseGamepad.cross;
            this.circle.isPressed = baseGamepad.circle;
            this.square.isPressed = baseGamepad.square;
            this.triangle.isPressed = baseGamepad.triangle;

            this.dpad.up.isPressed = baseGamepad.dpad_up;
            this.dpad.down.isPressed = baseGamepad.dpad_down;
            this.dpad.left.isPressed = baseGamepad.dpad_left;
            this.dpad.right.isPressed = baseGamepad.dpad_right;

            this.left_stick.x = baseGamepad.left_stick_x;
            this.left_stick.y = baseGamepad.left_stick_y;
            this.left_stick.press.isPressed = baseGamepad.left_stick_button;
            this.right_stick.x = baseGamepad.right_stick_x;
            this.right_stick.y = baseGamepad.right_stick_y;
            this.right_stick.press.isPressed = baseGamepad.right_stick_button;

            this.left_bumper.isPressed = baseGamepad.left_bumper;
            this.right_bumper.isPressed = baseGamepad.right_bumper;
            this.left_trigger.amt = baseGamepad.left_trigger;
            this.right_trigger.amt = baseGamepad.right_trigger;

            this.finger1.x = baseGamepad.touchpad_finger_1_x;
            this.finger1.y = baseGamepad.touchpad_finger_1_y;
            this.finger1.active = baseGamepad.touchpad_finger_1;

            this.finger2.x = baseGamepad.touchpad_finger_2_x;
            this.finger2.y = baseGamepad.touchpad_finger_2_y;
            this.finger2.active = baseGamepad.touchpad_finger_2;

            // Notify / call any observers for buttons
            if ((oldPad.cross && !baseGamepad.cross) || (!oldPad.cross && baseGamepad.cross)) {
                this.cross.call();
            }
            if ((oldPad.circle && !baseGamepad.circle) || (!oldPad.circle && baseGamepad.circle)) {
                this.circle.call();
            }
            if ((oldPad.square && !baseGamepad.square) || (!oldPad.square && baseGamepad.square)) {
                this.square.call();
            }
            if ((oldPad.triangle && !baseGamepad.triangle) || (!oldPad.triangle && baseGamepad.triangle)) {
                this.triangle.call();
            }

            if ((oldPad.left_bumper && !baseGamepad.left_bumper) || (!oldPad.left_bumper && baseGamepad.left_bumper)) {
                this.left_bumper.call();
            }
            if (oldPad.left_trigger != baseGamepad.left_trigger) {
                this.left_trigger.call();
            }
            if ((oldPad.right_bumper && !baseGamepad.right_bumper) || (!oldPad.right_bumper && baseGamepad.right_bumper)) {
                this.right_bumper.call();
            }
            if (oldPad.right_trigger != baseGamepad.right_trigger) {
                this.right_trigger.call();
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

            if ((oldPad.touchpad_finger_1 && !baseGamepad.touchpad_finger_1) || (!oldPad.touchpad_finger_1 && baseGamepad.touchpad_finger_1)) {
                this.finger1.call();
            }
            if ((oldPad.touchpad_finger_1_x != baseGamepad.touchpad_finger_1_x)) {
                this.finger1.call();
            }
            if ((oldPad.touchpad_finger_1_y != baseGamepad.touchpad_finger_1_y)) {
                this.finger1.call();
            }

            if ((oldPad.touchpad_finger_2 && !baseGamepad.touchpad_finger_2) || (!oldPad.touchpad_finger_2 && baseGamepad.touchpad_finger_2)) {
                this.finger2.call();
            }
            if ((oldPad.touchpad_finger_2_x != baseGamepad.touchpad_finger_2_x)) {
                this.finger2.call();
            }
            if ((oldPad.touchpad_finger_2_y != baseGamepad.touchpad_finger_2_y)) {
                this.finger2.call();
            }

            try {oldPad.copy(baseGamepad);} catch (RobotCoreException e) {e.printStackTrace();}
            try {sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
        }
    }
}
