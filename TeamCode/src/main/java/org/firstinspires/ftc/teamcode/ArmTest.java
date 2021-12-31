package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Arm Test Program", group = "Testing")
public class ArmTest extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        ElapsedTime runtime = new ElapsedTime();
        RoboHelper robot = new RoboHelper(hardwareMap, runtime);

        waitForStart();

        Arm arm = new Arm(hardwareMap);

        while(opModeIsActive()) {
            telemetry.addData("Arm (deg)", arm.armPosition);
            telemetry.addData("Arm (deg) (current): ", arm.arm.getCurrentPosition()/12);
            telemetry.addData("Wrist (Deg): ", arm.wrist.getPosition()*180);
            telemetry.update();
        }
        sleep(20*1000);
        //robot.arm.setPosition(45);

    }
}
