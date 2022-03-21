package org.firstinspires.ftc.teamcode.testing

import com.asiankoala.koawalib.command.CommandOpMode
import com.asiankoala.koawalib.hardware.KDevice
import com.asiankoala.koawalib.subsystem.drive.MecanumDriveCommand
import com.qualcomm.robotcore.eventloop.opmode.TeleOp


@TeleOp
class KoawaOp : CommandOpMode() {
    private lateinit var robot :  KoawalibTest

    override fun mInit() {
        robot = KoawalibTest()
        bindDrive()
    }

    private fun bindDrive() {
        robot.drive.setDefaultCommand(MecanumDriveCommand(robot.drive, driver.leftStick.yInverted.xInverted, driver.rightStick.xInverted))
    }
}