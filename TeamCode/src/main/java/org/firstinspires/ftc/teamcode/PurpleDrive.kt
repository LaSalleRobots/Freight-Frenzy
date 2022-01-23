package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime

@Autonomous(name = "RAINBOW park", group = "AI", preselectTeleOp = "Driver Controlled")
class PurpleDrive: LinearOpMode() {
    override fun runOpMode() {
        val runtime = ElapsedTime()
        val robot = RoboHelper(hardwareMap, runtime)
        waitForStart()
        robot.arm.gripper.close()
        robot.sleep(1.0)
        robot.arm.setPosition(robot.arm.TOP_LEVEL.toDouble())
        robot.drive.forward().goFor(0.5)
        robot.arm.setPosition(0.0)
    }
}