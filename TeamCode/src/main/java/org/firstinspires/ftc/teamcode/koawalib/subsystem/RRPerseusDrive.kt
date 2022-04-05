package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.acmerobotics.roadrunner.control.PIDCoefficients
import com.acmerobotics.roadrunner.drive.DriveSignal
import com.acmerobotics.roadrunner.geometry.Pose2d
import com.acmerobotics.roadrunner.localization.Localizer
import com.asiankoala.koawalib.hardware.motor.KMotor
import com.asiankoala.koawalib.math.Pose
import com.asiankoala.koawalib.roadrunner.drive.DriveConstants
import com.asiankoala.koawalib.roadrunner.drive.KMecanumDriveRR

class RRPerseusDrive(driveConstants: DriveConstants, frontLeft: KMotor, rearLeft: KMotor, rearRight: KMotor, frontRight: KMotor, driveLocalizer: Localizer, TRANSLATIONAL_PID: PIDCoefficients, HEADING_PID: PIDCoefficients) : KMecanumDriveRR(driveConstants, frontLeft, rearLeft, rearRight, frontRight, driveLocalizer, TRANSLATIONAL_PID, HEADING_PID) {
    override fun setDrivePower(drivePower: Pose2d) {
        super.setDrivePower(drivePower)
    }

    override fun setDriveSignal(driveSignal: DriveSignal) {
        super.setDriveSignal(driveSignal)
    }

    fun processPowers(drivePowers: Pose): List<Double> {
        val fl = drivePowers.y + drivePowers.x - drivePowers.heading
        val bl = drivePowers.y - drivePowers.x - drivePowers.heading
        val br = drivePowers.y + drivePowers.x + drivePowers.heading
        val fr = drivePowers.y - drivePowers.x + drivePowers.heading
        return listOf(fl, bl, br, fr)
    }
}