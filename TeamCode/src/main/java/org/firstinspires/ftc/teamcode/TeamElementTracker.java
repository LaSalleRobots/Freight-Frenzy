package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Rect;
import org.opencv.core.RotatedRect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvTracker;

import java.util.ArrayList;

public class TeamElementTracker extends OpenCvTracker {
    private static Mat rgb = new Mat();
    private final Mat output = new Mat();
    private final Mat orange = new Mat();
    private final Mat edges = new Mat();
    private final MatOfPoint2f largestContour2f = new MatOfPoint2f();

    // Tunables
    public Scalar lower = new Scalar(76, 0, 76);
    public Scalar upper = new Scalar(255, 0, 255);
    public Scalar cannyThresh = new Scalar(200, 255);
    public Scalar blur = new Scalar(7);

    public Scalar crop = new Scalar(100, 100);

    /** The bounds of the detected ring */
    public RotatedRect bounds = null;

    private ArrayList<MatOfPoint> contors = new ArrayList<>();

    @Override
    public Mat processFrame(Mat input) {
        contors.clear();
        bounds = null;

        /*
        Rect cropBox =
                new Rect(
                        (int) ((input.width() / 2) - (crop.val[0] / 2)),
                        (int) ((input.height() / 2) - (crop.val[1] / 2)),
                        (int) crop.val[0],
                        (int) crop.val[1]);

        input = new Mat(input, cropBox);
         */

        input.copyTo(output);

        Imgproc.cvtColor(input, rgb, Imgproc.COLOR_RGBA2RGB);
        Imgproc.blur(rgb, rgb, new Size(blur.val[0], blur.val[0]));
        Core.inRange(rgb, lower, upper, orange);

        // for display
        // output.release();
        // Core.bitwise_and(input, input, output, orange); // input, input, output, mask

        Imgproc.Canny(orange, edges, cannyThresh.val[0], cannyThresh.val[1]);
        Imgproc.findContours(edges, contors, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

        // Imgproc.drawContours(output, contors, -1, new Scalar(255,0,0));

        MatOfPoint largestContour = max();

        if (largestContour != null) {
            largestContour.convertTo(largestContour2f, CvType.CV_32F);
            bounds = Imgproc.minAreaRect(largestContour2f);
            Imgproc.rectangle(output, bounds.boundingRect(), new Scalar(255, 0, 0));
        }
        return output;
    }

    /**
     * Finds and returns the largest point in the contours. Preforms a linear search
     *
     * @return MatofPoint the largest point in the contours
     * @see MatOfPoint
     */
    private MatOfPoint max() {
        if (contors.size() > 0) {
            MatOfPoint largest = contors.get(0);
            for (MatOfPoint point : contors.subList(1, contors.size())) {
                if (point.size().area() > largest.size().area()) {
                    largest = point;
                }
            }
            return largest;
        }
        return null;
    }

    public enum Position {
        Left,
        Center,
        Right
    }

    public Position getPosition() {
        if (bounds == null || bounds.center.x <= 200) {
            return Position.Left;
        } else if (bounds.center.x >= 200 && bounds.center.x <= 800) {
            return Position.Center;
        } else {
            return Position.Right;
        }
    }
}
