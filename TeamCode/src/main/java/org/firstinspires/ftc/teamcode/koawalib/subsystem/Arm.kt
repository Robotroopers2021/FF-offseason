package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.MotionProfileConfig
import com.asiankoala.koawalib.control.PIDFConfig
import com.asiankoala.koawalib.control.feedforward.ArmFeedforward
import com.asiankoala.koawalib.control.feedforward.FeedforwardCoefficients
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import com.asiankoala.koawalib.subsystem.old.MotorSubsystem
import com.asiankoala.koawalib.subsystem.old.MotorSubsystemConfig
import org.firstinspires.ftc.teamcode.util.Encoder

class Arm(config: MotorSubsystemConfig) : MotorSubsystem(config) {
    companion object ArmConstants {
        const val topPosition = 0.0
        const val midPosition = 0.0
        const val bottomPosition = 0.0
        const val sharedPosition = 0.0
        const val startPosition = 0.0
        const val armIntakePos = 0.0
    }
}

