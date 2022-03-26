package org.firstinspires.ftc.teamcode

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.subsystem.drive.MecanumDriveCommand
import com.qualcomm.robotcore.eventloop.opmode.TeleOp

@TeleOp
class KousakaTeleOp : CommandOpMode() {
    private lateinit var robot: Koawa

    override fun mInit() {
        robot = Koawa()
        robot.drive.setDefaultCommand(MecanumDriveCommand(robot.drive, driver.leftStick.yInverted.xInverted, driver.rightStick.xInverted))
    }

    override fun mLoop() {
        robot.imu.periodic()
    }
}