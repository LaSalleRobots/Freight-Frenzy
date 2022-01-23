package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime

@Autonomous(name = "Full Auto Blue (right)", group = "AI", preselectTeleOp = "Driver Controlled")
class BlueRightAuto : LinearOpMode() {
    @Throws(InterruptedException::class)
    override fun runOpMode() {
        val runtime = ElapsedTime()
        val robot = RoboHelper(hardwareMap, runtime)
        waitForStart()
        robot.arm.gripper.close()
        robot.sleep(1.0)
        robot.arm.setPosition(robot.arm.TOP_LEVEL + 10)
        robot.drive.forward().goFor(0.3)
        robot.sleep(1.0)
        robot.drive.left().goFor(0.7)
        robot.sleep(1.0)
        robot.drive.speed = 0.2
        robot.drive.forward().goFor(0.3)
        robot.sleep(1.0)
        robot.arm.gripper.release()
        robot.sleep(1.0)
        robot.drive.speed = 1.0
        robot.drive.right().goFor(1.5)
        robot.drive.backward().goFor(0.5)
        robot.drive.speed = 0.2
        robot.drive.backward().goFor(0.2)
        robot.drive.rotateLeft().goFor(0.05)
        robot.drive.backward().goFor(0.1)
        robot.startSpinnerOther(true)
        robot.sleep(3.0)
        robot.drive.speed = 0.5
        robot.drive.forward().goFor(1.0)
        robot.drive.right().goFor(0.6)
        robot.arm.setPosition(0.0)
    }
}