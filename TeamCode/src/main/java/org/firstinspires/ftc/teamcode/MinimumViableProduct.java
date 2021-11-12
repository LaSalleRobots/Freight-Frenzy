package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "MinimumViableProduct", group = "Pre-Programed")
public class MinimumViableProduct extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
    	RoboHelper robot = new RoboHelper(hardwareMap, runtime);
	
		waitForStart();
		
		robot.moveForwards().runFor(0.1);
		robot.rotateLeft().runFor(0.2);
		robot.moveForwards().runFor(5);


    }
}
