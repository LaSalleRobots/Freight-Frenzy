package org.firstinspires.ftc.teamcode;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.function.Consumer;

public class Trigger {
    public float amt;

    private Consumer<Trigger> bound;

    public void bind(Consumer<Trigger> func) {
        bound = func;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void call() {
        if (this.bound != null) {
            this.bound.accept(this);
        }
    }
}
