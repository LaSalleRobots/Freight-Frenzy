package org.firstinspires.ftc.teamcode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Consumer;

public class DPad {
    public Button up;
    public Button down;
    public Button left;
    public Button right;

    public DPad() {
        up = new Button();
        down = new Button();
        left = new Button();
        right = new Button();
    }

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
