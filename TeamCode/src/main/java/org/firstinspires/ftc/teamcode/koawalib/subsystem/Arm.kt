package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import org.firstinspires.ftc.teamcode.util.Encoder

class Arm(val motor : KMotorEx) : DeviceSubsystem() {
    companion object ArmConstants {
        const val topPosition = 10.0
        const val midPosition = -15.0
        const val bottomPosition = -30.0
        const val sharedPosition = 25.0 -55
        const val sharedMid = 20.0 -55
        const val sharedLow = 15.0 -55
        const val sharedHigh = 25.0 -55
        const val startPosition = -35.0
        const val armIntakePos = -35.0
    }

}


