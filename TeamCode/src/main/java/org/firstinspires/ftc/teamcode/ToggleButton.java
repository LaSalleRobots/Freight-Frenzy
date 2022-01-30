package org.firstinspires.ftc.teamcode;

public class ToggleButton {

    public boolean lock = false;
    public boolean output = false;

    public ToggleButton(boolean initialState) {
        lock = initialState;
        output = initialState;
    }

    public  ToggleButton() {

    }

    public boolean checkButton(boolean pressed) {
        if (pressed) {
            if (!lock) {
                output = !output;
            }
            lock = true;
        } else {
            lock = false;
        }

        return output;
    }

    @Override
    public String toString() {
        return "ButtonLocker{" +
                "lock=" + lock +
                ", output=" + output +
                '}';
    }
}
