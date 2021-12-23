package org.firstinspires.ftc.teamcode;

/* 2019-2022 FTC Robotics Freight-Frenzy
 * (c) 2019-2022 La Salle Robotics
 * Developed for the Freight Frenzy competition
 */

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class RoboHelper {

    private final ElapsedTime runtime;


    private final DcMotor leftFront;
    private final DcMotor rightFront;
    private final DcMotor leftBack;
    private final DcMotor rightBack;
	private final DcMotor plateSpinner;

    public Arm arm;

	public BNO055IMU imu = null;

    private final double FRICTION_COEF = 1.75; // the distance the robot goes in 1 second (in feet)

    private double armDeg = 0; // arm is at resting at the bottom
    private double flP = 0;
    private double blP = 0;
    private double frP = 0;
    private double brP = 0;
    private final boolean gripperState = false;
    private final boolean gripperTransitioning = false;

    public double speedScale = 1; // keep between 0 and 1

    // setup class initializer
    public RoboHelper(HardwareMap hardwareMap, ElapsedTime runtime) {
        this.runtime = runtime;

        // Setup Motors
        this.leftFront = hardwareMap.get(DcMotor.class, "fL");
        this.rightFront = hardwareMap.get(DcMotor.class, "fR");
        this.leftBack = hardwareMap.get(DcMotor.class, "bL");
        this.rightBack = hardwareMap.get(DcMotor.class, "bR");
		this.plateSpinner = hardwareMap.get(DcMotor.class, "spinner");
        this.arm = new Arm(hardwareMap);

        // Set Directions
        this.leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
        this.leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        this.rightFront.setDirection(DcMotorSimple.Direction.FORWARD);
        this.rightBack.setDirection(DcMotorSimple.Direction.FORWARD);
		this.plateSpinner.setDirection(DcMotorSimple.Direction.FORWARD);


		// Setup Sensors
        this.imu = hardwareMap.get(BNO055IMU.class, "imu");
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.RADIANS;
        //parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        this.imu.initialize(parameters);
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
        calculateDirections(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, false);
        applyPower();
        return this;
    }

    public RoboHelper calculateDirections(double x, double y, double turn, boolean field) {
        if (field) {
            calculateDirectionsFieldCentric(x,y,turn);
        } else {
            calculateDirectionsRobotCentric(x,y,turn);
        }
        return this;
    }

    public RoboHelper calculateDirectionsFieldCentric(double x, double y, double turn) {

        double phi = (angle(x,y)-getHeading());

        this.blP =
                magnitude(x,y)
                        * Math.sin( phi + (Math.PI / 4))
                        + turn; // flP
        this.flP =
                magnitude(x,y)
                        * Math.sin(phi - (Math.PI / 4))
                        + turn; // blP

        this.brP =
                magnitude(x,y)
                        * Math.sin(phi + (Math.PI / 4))
                        - turn; // frP
        this.frP =
                magnitude(x,y)
                        * Math.sin(phi - (Math.PI / 4))
                        - turn; // brP

        return this;
    }

    public RoboHelper calculateDirectionsRobotCentric(double x, double y, double turn) {

        double phi = (angle(x,y));

        this.blP =
                magnitude(x,y)
                        * Math.sin( phi + (Math.PI / 4))
                        + turn; // flP
        this.flP =
                magnitude(x,y)
                        * Math.sin(phi - (Math.PI / 4))
                        + turn; // blP

        this.brP =
                magnitude(x,y)
                        * Math.sin(phi + (Math.PI / 4))
                        - turn; // frP
        this.frP =
                magnitude(x,y)
                        * Math.sin(phi - (Math.PI / 4))
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

    public double getHeading() {
        Orientation orientation = this.imu.getAngularOrientation();
        return orientation.firstAngle;
    }

    public RoboHelper powerOff() {
        flP = 0;
        blP = 0;
        frP = 0;
        brP = 0;
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
        return this;
    }

    public RoboHelper applyPower() {
        leftFront.setPower(-flP);
        rightFront.setPower(-frP);
        leftBack.setPower(-blP);
        rightBack.setPower(-brP);
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
        sleep(runningDistance * FRICTION_COEF);
        powerOff();
        return this;
    }

    public RoboHelper moveForwards() {
        calculateDirections(0, -1, 0, false);
        applyPower();
        return this;
    }

    public RoboHelper moveBackwards() {
        calculateDirections(0, 1, 0, false);
        applyPower();
        return this;
    }

    public RoboHelper moveLeft() {
        calculateDirections(-1, 0, 0, false);
        applyPower();
        return this;
    }

    public RoboHelper moveRight() {
        calculateDirections(1, 0, 0, false);
        applyPower();
        return this;
    }

    public RoboHelper moveBackwardsLeft() {
        calculateDirections(-1, -1, 0, false);
        applyPower();
        return this;
    }

    public RoboHelper moveBackwardsRight() {
        calculateDirections(1, -1, 0, false);
        applyPower();
        return this;
    }

    public RoboHelper moveForwardsLeft() {
        calculateDirections(-1, 1, 0, false);
        applyPower();
        return this;
    }

    public RoboHelper moveForwardsRight() {
        calculateDirections(1, 1, 0, false);
        applyPower();
        return this;
    }

    public RoboHelper rotateLeft() {
        calculateDirections(0, 0, -1, false);
        applyPower();
        return this;
    }

    public RoboHelper rotateRight() {
        calculateDirections(0, 0, 1, false);
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

}
