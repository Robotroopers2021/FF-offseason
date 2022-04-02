package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.MotionProfileConfig
import com.asiankoala.koawalib.control.PIDFConfig
import com.asiankoala.koawalib.control.feedforward.FeedforwardCoefficients
import com.asiankoala.koawalib.control.feedforward.MotorFeedforward
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.sensor.KLimitSwitch
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import com.asiankoala.koawalib.subsystem.odometry.Encoder

class Turret(private val motor: KMotorEx, private val limitSwitch: KLimitSwitch, private val turretEncoder : Encoder) : DeviceSubsystem() {
    @Config
    companion object TurretConstants {
        @JvmField var kp = 0.015
        @JvmField var ki = 0.0
        @JvmField var kd = 0.0
        @JvmField var kcos = 0.0
        @JvmField var kStatic = 0.0
        val config = PIDFConfig(kp, ki,kd,
            MotorFeedforward(FeedforwardCoefficients(0.0,0.0),kStatic),
            0.0,0.0,0.0)
        const val zeroPosition = 0.0
        const val homeAngle = 0.0
        const val allianceAngleBlue = 0.0
        const val sharedAngleBlue = 0.0
        const val allianceAngleRed = 0.0
        const val sharedAngleRed = 0.0
    }

    val isAtTarget get() = motor.isAtTarget

    val currentPosition get() = turretEncoder.position

    fun setTurretAngle(angle: Double) {
        motor.setPIDTarget(angle)
    }

    override fun periodic() {
        motor.update(turretEncoder)
    }
}