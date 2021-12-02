package org.firstinspires.ftc.teamcode;

/* 2019-2021 FTC Robotics Freight-Frenzy
 * (c) 2019-2021 La Salle Robotics
 * Developed for the Freight Frenzy competition
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RoboHelper {

    private ElapsedTime runtime;

    // setup motors
    private DcMotor leftFront = null;
    private DcMotor rightFront = null;
    private DcMotor leftBack = null;
    private DcMotor rightBack = null;
	private DcMotor plateSpinner = null;
	private DcMotor gripperLeft = null;
	private DcMotor gripperRight = null;
	private DcMotor swing = null;

    private double fixionCoef = 1.75; // the distance the robot goes in 1 second (in feet)

    private double flP = 0;
    private double blP = 0;
    private double frP = 0;
    private double brP = 0;
    private boolean gripperState = false;
    private boolean gripperTransitioning = false;

    public double speedScale = 1; // keep between 0 and 1

    // setup class initializer
    public RoboHelper(HardwareMap hardwareMap, ElapsedTime runtime) {
        this.runtime = runtime;

        // setup motors
        this.leftFront = hardwareMap.get(DcMotor.class, "fL");
        this.rightFront = hardwareMap.get(DcMotor.class, "fR");
        this.leftBack = hardwareMap.get(DcMotor.class, "bL");
        this.rightBack = hardwareMap.get(DcMotor.class, "bR");
		this.plateSpinner = hardwareMap.get(DcMotor.class, "spinner");
		this.gripperLeft = hardwareMap.get(DcMotor.class, "gripperLeft");
		this.gripperRight = hardwareMap.get(DcMotor.class, "gripperRight");
		this.swing = hardwareMap.get(DcMotor.class, "swing");

        // Set Directions
        this.leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        this.leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        this.rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
		this.plateSpinner.setDirection(DcMotorSimple.Direction.FORWARD);
		this.gripperLeft.setDirection(DcMotorSimple.Direction.FORWARD);
		this.gripperRight.setDirection(DcMotorSimple.Direction.REVERSE);
		this.swing.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    /*
     * This section is for making the driver programing experience simpler
     */

    public double magnitude(double x, double y) {
        return -Math.hypot(x, y);
    }

    public double angle(double x, double y) {
        return Math.atan2(y, x);
    }


    public double getGamepadMoveMagnitude(Gamepad gamepad) {
        return -Math.hypot(gamepad.left_stick_x, gamepad.left_stick_y) * 1.5;
    }

    public double getGamepadTurnMagnitude(Gamepad gamepad) {
        return gamepad.right_stick_x;
    }

    public double getGamepadMoveAngle(Gamepad gamepad) {
        return Math.atan2(gamepad.left_stick_y, gamepad.left_stick_x);
    }

    // handleGamepads the second gamepad is currently ignored for this input code
    public RoboHelper handleGamepads(Gamepad gamepad1, Gamepad gamepad2) {
        calculateDirections(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x );
        applyPower();
        return this;
    }

    public RoboHelper calculateDirections(double x, double y, double turn) {

        this.blP =
                magnitude(x,y)
                        * Math.sin(angle(x,y) + (Math.PI / 4))
                        + turn; // flP
        this.flP =
                magnitude(x,y)
                        * Math.sin(angle(x,y) - (Math.PI / 4))
                        + turn; // blP

        this.brP =
                magnitude(x,y)
                        * Math.sin(angle(x,y) + (Math.PI / 4))
                        - turn; // frP
        this.frP =
                magnitude(x,y)
                        * Math.sin(angle(x,y) - (Math.PI / 4))
                        - turn; // brP

        return this;
    }

    /*
     * This section is for making autonomous programming simpler
     */

    public void sleep(double sleepTime) {
        double time = runtime.time();
        double initTime = time;

        while (time <= initTime + sleepTime) {
            time = runtime.time();
        }
    }

    public RoboHelper powerOff() {
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        return this;
    }

    public RoboHelper applyPower() {
        leftFront.setPower(flP);
        rightFront.setPower(frP);
        leftBack.setPower(blP);
        rightBack.setPower(brP);
        return this;
    }

    public RoboHelper runFor(double runTime) {
        applyPower();
        sleep(runTime);
        powerOff();
        return this;
    }

    public RoboHelper runDist(double runningDistance) {
        applyPower();
        sleep(runningDistance * fixionCoef);
        powerOff();
        return this;
    }

    public RoboHelper moveForwards() {
        calculateDirections(0, -1, 0);
        applyPower();
        return this;
    }

    public RoboHelper moveBackwards() {
        calculateDirections(0, 1, 0);
        applyPower();
        return this;
    }

    public RoboHelper moveLeft() {
        calculateDirections(-1, 0, 0);
        applyPower();
        return this;
    }

    public RoboHelper moveRight() {
        calculateDirections(1, 0, 0);
        applyPower();
        return this;
    }

    public RoboHelper moveBackwardsLeft() {
        calculateDirections(-1, -1, 0);
        applyPower();
        return this;
    }

    public RoboHelper moveBackwardsRight() {
        calculateDirections(1, -1, 0);
        applyPower();
        return this;
    }

    public RoboHelper moveForwardsLeft() {
        calculateDirections(-1, 1, 0);
        applyPower();
        return this;
    }

    public RoboHelper moveForwardsRight() {
        calculateDirections(1, 1, 0);
        applyPower();
        return this;
    }

    public RoboHelper rotateLeft() {
        calculateDirections(0, 0, -1);
        applyPower();
        return this;
    }

    public RoboHelper rotateRight() {
        calculateDirections(0, 0, 1);
        applyPower();
        return this;
    }

	public RoboHelper startSpinner() {
		this.plateSpinner.setPower(1);
		return this;
	}

	public RoboHelper startSpinnerOther() {
        this.plateSpinner.setPower(-1);
        return this;
    }

	public RoboHelper stopSpinner() {
		this.plateSpinner.setPower(0);
		return this;
	}

	public RoboHelper gripperClose() {
        // prevent this from running while it is running.
        while (gripperTransitioning) {}
        gripperTransitioning=true;
        this.gripperLeft.setPower(1);
        this.gripperRight.setPower(1);
        this.sleep(0.1);
        this.gripperLeft.setPower(0);
        this.gripperRight.setPower(0);

        gripperTransitioning=false;
        return this;
    }

    public RoboHelper gripperOpen() {
        while (gripperTransitioning) {}
        gripperTransitioning=true;
        this.gripperLeft.setPower(-1);
        this.gripperRight.setPower(-1);
        this.sleep(0.1);
        this.gripperLeft.setPower(0);
        this.gripperRight.setPower(0);
        gripperTransitioning=false;
        return this;
    }

    public RoboHelper gripperToggle() {
        if (gripperState) {
            gripperClose();
        } else {
            gripperOpen();
        }
        gripperState = !gripperState;
        return this;
    }

    public RoboHelper raiseArm() {
        this.swing.setPower(0.5);
        return this;
    }

    public RoboHelper lowerArm() {
        this.swing.setPower(-0.5);
        return this;
    }

    public RoboHelper stopArm() {
        this.swing.setPower(0);
        return this;
    }

}
