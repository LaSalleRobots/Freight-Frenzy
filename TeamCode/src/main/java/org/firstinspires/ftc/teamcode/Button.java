package org.firstinspires.ftc.teamcode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Consumer;

public class Button {
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
