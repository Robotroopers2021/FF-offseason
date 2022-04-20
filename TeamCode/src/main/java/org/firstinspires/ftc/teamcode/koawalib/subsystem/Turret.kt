package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import com.asiankoala.koawalib.subsystem.odometry.KEncoder

class Turret(val motor : KMotorEx) : DeviceSubsystem() {
    companion object TurretConstants {
        const val turretZeroPosition = 0.0
        const val turretHomeAngle = 0.0
        const val allianceAngleBlue = 125.0
        const val sharedAngleBlue = -90.0
        const val allianceAngleRed = 0.0
        const val sharedAngleRed = 90.0
        const val sharedHomeAngle = 0.0
    }
}

