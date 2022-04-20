package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import org.firstinspires.ftc.teamcode.util.Encoder

class Arm(val motor : KMotorEx) : DeviceSubsystem() {
    companion object ArmConstants {
        const val topPosition = -25.0
        const val midPosition = 60.0 -52
        const val bottomPosition = 25.0 -52
        const val sharedPosition = 25.0 -52
        const val sharedMid = 20.0 -52
        const val sharedLow = 15.0 -52
        const val sharedHigh = 25.0 -52
        const val startPosition = 37.0 -52
        const val armIntakePos = 23.75 -52
    }

}


