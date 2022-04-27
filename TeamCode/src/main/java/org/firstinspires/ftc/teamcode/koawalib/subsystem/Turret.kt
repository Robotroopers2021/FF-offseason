package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Turret(val motor : KMotorEx) : DeviceSubsystem() {
    companion object TurretConstants {
        const val turretZeroPosition = 0.0
        const val turretHomeAngle = 0.0
        const val allianceAngleBlue = 90.0
        const val sharedAngleBlue = 90.0
        const val allianceAngleRed = 270.0
        const val sharedAngleRed = 270.0
    }
}

