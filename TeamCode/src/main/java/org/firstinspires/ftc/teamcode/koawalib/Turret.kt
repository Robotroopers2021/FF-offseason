package org.firstinspires.ftc.teamcode.koawalib

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.control.MotionProfileConfig
import com.asiankoala.koawalib.control.PIDFConfig
import com.asiankoala.koawalib.control.feedforward.DisableFeedforward
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.sensor.KLimitSwitch
import com.asiankoala.koawalib.subsystem.ZeroableDeviceSubsystem

class Turret(motor: KMotorEx, limitSwitch: KLimitSwitch) : ZeroableDeviceSubsystem(motor, limitSwitch) {
    @Config
    companion object TurretConstants {
    @JvmField val config = MotionProfileConfig(PIDFConfig(0.0,0.0,0.0, DisableFeedforward(),0.0,0.0,0.0),
        0.0, 0.0)
        const val zeroPosition = 0.0
        const val homeAngle = 0.0
        const val allianceAngle = 0.0
        const val sharedAngle = 0.0
    }

    val isAtTarget get() = motor.isAtTarget()

    fun setTurretAngle(angle: Double) {
        motor.setMotionProfileTarget(angle)
    }

    override val zeroPosition: Double
        get() = TurretConstants.zeroPosition
}