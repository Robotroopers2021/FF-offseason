package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.acmerobotics.dashboard.config.Config
import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.control.MotionProfileConfig
import com.asiankoala.koawalib.control.PIDFConfig
import com.asiankoala.koawalib.control.feedforward.FeedforwardCoefficients
import com.asiankoala.koawalib.control.feedforward.MotorFeedforward
import com.asiankoala.koawalib.hardware.motor.KMotorEx
import com.asiankoala.koawalib.hardware.sensor.KLimitSwitch
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import com.asiankoala.koawalib.subsystem.odometry.KEncoder
import com.asiankoala.koawalib.subsystem.old.MotorSubsystem
import com.asiankoala.koawalib.subsystem.old.MotorSubsystemConfig

class Turret(config : MotorSubsystemConfig) : MotorSubsystem(config) {
    companion object TurretConstants {
        const val turretZeroPosition = 0.0
        const val turretHomeAngle = 0.0
        const val allianceAngleBlue = 125.0
        const val sharedAngleBlue = 90.0
        const val allianceAngleRed = 0.0
        const val sharedAngleRed = 0.0
        const val sharedHomeAngle = 0.0
    }
}

