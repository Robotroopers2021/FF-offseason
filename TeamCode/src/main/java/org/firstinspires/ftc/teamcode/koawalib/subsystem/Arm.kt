package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.MotionProfileConfig
import com.asiankoala.koawalib.control.PIDFConfig
import com.asiankoala.koawalib.control.feedforward.ArmFeedforward
import com.asiankoala.koawalib.control.feedforward.FeedforwardCoefficients
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import org.firstinspires.ftc.teamcode.util.Encoder

class Arm(private val motor: KMotorEx, private val armEncoder: com.asiankoala.koawalib.subsystem.odometry.Encoder) : DeviceSubsystem() {
    @Config
    companion object ArmConstants {
        @JvmField var kp = 0.0
        @JvmField var ki = 0.0
        @JvmField var kd = 0.0
        @JvmField var kcos = 0.275
        @JvmField var kStatic = 0.0
        val config = PIDFConfig(kp,ki,kd,
            ArmFeedforward(kcos, FeedforwardCoefficients(0.0,0.0), kStatic),
        2.0, Double.NaN, 213.5/90.0)
       const val topPosition = -90.0
        const val midPosition = 0.0
        const val bottomPosition = 0.0
        const val sharedPosition = 0.0
        const val startPosition = 0.0
    }

    val isAtTarget get() = motor.isAtTarget

    val currentPosition get() = armEncoder.position  / config.ticksPerUnit

    fun setArmAngle(angle: Double) {
        motor.setPIDTarget(angle)
    }

    override fun periodic() {
        motor.update(armEncoder)
    }

}
