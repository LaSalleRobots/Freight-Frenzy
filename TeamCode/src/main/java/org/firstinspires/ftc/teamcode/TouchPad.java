package org.firstinspires.ftc.teamcode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Consumer;

public class TouchPad {
    public float x;
    public float y;
    public boolean active;

    private Consumer<TouchPad> bound;

    public void bind(Consumer<TouchPad> func) {bound = func;}

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void call() {
        if (this.bound != null) {
            this.bound.accept(this);
        }
    }
}
