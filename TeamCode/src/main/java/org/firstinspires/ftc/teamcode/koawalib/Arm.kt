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
        @JvmField var kp = 0.015
        @JvmField var ki = 0.0
        @JvmField var kd = 0.0
        @JvmField var kcos = 0.0
        @JvmField var kStatic = 0.0
        @JvmField var maxVelocity = 30.0
        @JvmField var maxAcceleration = 30.0
        val config = MotionProfileConfig(PIDFConfig(kp,ki,kd,
            ArmFeedforward(kcos, FeedforwardCoefficients(0.0,0.0), kStatic),
            2.0, Double.NaN,592.0/90.0),maxVelocity, maxAcceleration)
       const val topPosition = -60.0
        const val midPosition = 0.0
        const val bottomPosition = 0.0
        const val sharedPosition = 0.0
        const val zeroPosition = 0.0
    }

    val isAtTarget get() = motor.isAtTarget()

    val currentPosition get() = motor.position  / config.pidConfig.ticksPerUnit

    fun setArmAngle(angle: Double) {
        motor.setMotionProfileTarget(angle)
    }

    override fun periodic() {
        motor.update()
    }

}
