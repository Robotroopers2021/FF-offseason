package org.firstinspires.ftc.teamcode.koawalib

import com.acmerobotics.dashboard.config.Config
import com.acmerobotics.dashboard.telemetry.TelemetryPacket
import com.asiankoala.koawalib.control.MotionProfileConfig
import com.asiankoala.koawalib.control.PIDFConfig
import com.asiankoala.koawalib.control.feedforward.ArmFeedforward
import com.asiankoala.koawalib.control.feedforward.FeedforwardCoefficients
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Arm(private val motor: KMotorEx) : DeviceSubsystem() {
    @Config
    companion object ArmConstants {
        @JvmField val config = MotionProfileConfig(PIDFConfig(0.0,0.0,0.0,
            ArmFeedforward(0.0, FeedforwardCoefficients(0.0,0.0), 0.0),
            0.0,0.0,0.0),0.0,0.0)
       const val topPosition = 0.0
        const val midPosition = 0.0
        const val bottomPosition = 0.0
        const val sharedPosition = 0.0
        const val zeroPosition = 0.0


    }

    val isAtTarget get() = motor.isAtTarget()

    fun setArmAngle(angle: Double) {
        motor.setMotionProfileTarget(angle)
    }

    fun updateTelemetry() {
        val packet = TelemetryPacket()
        packet.put("config", config)

    }
}
