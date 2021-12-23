package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name= "Encoder Testing")
public class EncoderOpMode extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotor arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        waitForStart();
        telemetry.addData("ARM","current: %7d, target: %7d", arm.getCurrentPosition(), arm.getTargetPosition());
        telemetry.update();

        arm.setPower(.5);
        arm.setTargetPosition(90*12);
        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        sleep((long)(1000*3.0));

        //arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
       arm.setTargetPosition(12*45);
       arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        while (opModeIsActive()) {
            telemetry.addData("ARM","current: %7d, target: %7d", arm.getCurrentPosition(), arm.getTargetPosition());
            telemetry.update();
        }
    }
}
