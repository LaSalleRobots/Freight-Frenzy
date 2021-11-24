package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Red Autonomous", group = "Pre-Programed")
public class RedAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
    	ElapsedTime runtime = new ElapsedTime();
    	RoboHelper robot = new RoboHelper(hardwareMap, runtime);
    	robot.speedScale = 0.7;
	
		waitForStart();
		
		robot.startSpinner().runFor(5).stopSpinner();
		robot.moveBackwards().runFor(0.25);
		robot.rotateRight().runFor(0.35);
		robot.moveForwards().runFor(1.5);
		robot.rotateLeft().runFor(0.45);
		robot.moveForwards().runFor(1);

    }
}
