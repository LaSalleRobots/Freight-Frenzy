package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime

@Autonomous(name = "Blue Auto (Left)", group = "Proc", preselectTeleOp = "Driver Controlled")
class BlueLeftAuto: LinearOpMode() {
    override fun runOpMode() {
        var elapsedTime = ElapsedTime()
        var robot = RoboHelper(hardwareMap, elapsedTime);

        waitForStart()
        robot.arm.gripper.close()
        robot.sleep(1.0)
        robot.arm.setPosition((robot.arm.TOP_LEVEL + 10).toDouble())
        robot.drive.forward().goFor(0.6)
        robot.sleep(0.5)
        robot.drive.rotateRight().goFor(0.3)
        robot.sleep(0.5)
        robot.drive.speed = 0.2
        robot.drive.forward().goFor(1.0)
        robot.sleep(0.5)
    }


}