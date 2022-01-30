package org.firstinspires.ftc.teamcode

import com.qualcomm.robotcore.eventloop.opmode.Autonomous
import com.qualcomm.robotcore.eventloop.opmode.Disabled
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
import com.qualcomm.robotcore.util.ElapsedTime

@Disabled
class RedLeftAuto : LinearOpMode() {
    override fun runOpMode() {
        val runtime = ElapsedTime()
        val robot = RoboHelper(hardwareMap, runtime)
        waitForStart()
        robot.arm.gripper.close()
        robot.sleep(1.0)
        robot.arm.setPosition(robot.arm.MIDDLE_LEVEL)
        robot.drive.speed = 1.0
        robot.drive.forward().goFor(0.7) // drive to the shipping hub
        robot.sleep(0.5) //DEBUG: sleep and kill momentum
        robot.drive.speed = 0.75
        robot.drive.rotateRight().goFor(0.3) // 90 Deg turn TUNE ME
        robot.drive.speed = 1.0
        robot.sleep(0.5) // stop any momentum/velocity
        robot.arm.setPosition(robot.arm.TOP_LEVEL + 10)
        robot.sleep(1.0) // carefully get ready
        robot.drive.forward().goFor(0.2) // nudge over to the shipping hub
        robot.sleep(1.0)
        robot.arm.gripper.release() // drop our payload
        robot.sleep(0.5)
        robot.drive.backward().goFor(0.8) // back towards the wall by the carousel
        robot.sleep(1.0)
        robot.drive.right().goFor(1.35) // head to the carousel
        robot.sleep(0.5)
        robot.drive.speed = 0.2
        robot.drive.backward().goFor(0.3)
        robot.drive.right().goFor(0.5) // back into the carefully
        robot.startSpinner(true)
        robot.sleep(3.0)
        robot.drive.speed = 1.0
        robot.drive.left().goFor(0.8)
        robot.arm.setPositionAsyncUnbounded(0.0)
        robot.sleep(2.0)
    }
}