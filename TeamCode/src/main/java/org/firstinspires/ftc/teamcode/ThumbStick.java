package org.firstinspires.ftc.teamcode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Consumer;

public class ThumbStick {
    public float x;
    public float y;
    public Button press;

    public ThumbStick() {
        press = new Button();
    }

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
