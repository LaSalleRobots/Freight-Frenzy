package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class Arm {

    public Gripper gripper;

    private final Servo wrist;
    private final DcMotor arm;

    // Internal State
    private boolean clawOpen;
    private double armPosition = 0;
    public int armDelta = 10; // how much the arm should raise/lower by when asking to do those ops
    public int tickAccuracy = 20;

    public Arm (HardwareMap hardwareMap) {
        arm = hardwareMap.get(DcMotor.class, "arm");

        // Setup Servos
        this.wrist = hardwareMap.get(Servo.class, "wrist");

        // Setup Encoders
        this.arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void setPositionAsync(double degrees) {
        this.armPosition = bound(degrees);
        this.arm.setTargetPosition(convertDegreesToTicks(this.armPosition));
        this.arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.updateWrist();
    }

    public void setPosition(double degrees) {
        // this is the same as the async version except it waits until the arm has gotten within the tick accuracy
        this.setPositionAsync(degrees);
        while (
                Math.abs(this.arm.getCurrentPosition() - this.arm.getTargetPosition()) <= this.tickAccuracy
        ) {}
    }

    public int convertDegreesToTicks(double degrees) {
        return (int)(degrees * 12);
    }

    public double calculateWrist() {
        return convertDegreesToTicks(this.armPosition) - 45;
    }

    public void updateWrist() {
        this.wrist.setPosition(calculateWrist());
    }

    public void raise() {
        this.setPositionAsync(armPosition + armDelta);
    }

    public void lower() {
        this.setPositionAsync(armPosition - armDelta);
    }

    public double bound(double degrees) {
        // this will keep the arm within its ok range of motion
        if (degrees >= 270) {
            return 270;
        } else if (degrees <= 0) {
            return 0;
        }
        return degrees;
    }

    public static class Gripper {
        private final Servo clawLeft;
        private final Servo clawRight;
        private boolean clawOpen;

        public Gripper(HardwareMap hardwareMap) {
            this.clawLeft = hardwareMap.get(Servo.class, "clawLeft");
            this.clawRight = hardwareMap.get(Servo.class, "clawRight");
        }


        public void close() {
            this.clawLeft.setPosition(180-45);
            this.clawRight.setPosition(45);
            this.clawOpen = true;
        }

        public void open() {
            this.clawLeft.setPosition(180);
            this.clawRight.setPosition(0);
            this.clawOpen = false;
        }

        public void toggle() {
            if (clawOpen) {
                this.close();
            } else {
                this.open();
            }
        }
    }
}

