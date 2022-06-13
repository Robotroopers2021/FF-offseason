package org.firstinspires.ftc.teamcode.stinger.subsystems

import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Arm(val motor : KMotorEx) : DeviceSubsystem() {
    companion object ArmConstants {
        const val depositAngle = 94.0
        const val restAngle = -55.0
        const val sharedAngle = 172.0
        const val sharedAngleAlliance = 178.0
        const val sharedAngleEnemy = 164.0
        const val middlePos = 134.0
        const val failPos = -200.0
    }
}