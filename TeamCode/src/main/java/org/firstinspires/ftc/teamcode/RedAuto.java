package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "Red (Spin) Autonomous", group = "Pre-Programed")
public class RedAuto extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
    	ElapsedTime runtime = new ElapsedTime();
    	RoboHelper robot = new RoboHelper(hardwareMap, runtime);
    	robot.speedScale = 1;
	
		waitForStart();
		
		robot.moveForwards().runFor(.1);
		robot.moveLeft().runFor(.2);
		robot.speedScale = 0.05;
		for (int i = 0; i < 4; i++) {
			robot.moveBackwards();
			robot.startSpinnerOther().runFor(3.6);
			robot.stopSpinner().runFor(0.7);
		}
		robot.speedScale = .4;
		robot.moveForwards().runFor(.2);
    }
}
