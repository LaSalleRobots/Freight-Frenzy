package org.firstinspires.ftc.teamcode;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvTracker;

public class BasicTracker extends OpenCvTracker {
    @Override
    public Mat processFrame(Mat input) {
        return input;
    }
}
