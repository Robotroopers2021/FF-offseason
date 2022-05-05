package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Clocking (private val servo : KServo) :DeviceSubsystem() {

    companion object ClockingConstants{
        const val zeroPosition = 0.0
        const val depositPosition = 0.35
        const val intakePosition = 0.40
        const val liftPosition = 0.65
        const val capPosition = 0.55
    }

    fun zero() {
        servo.position = zeroPosition
    }

    fun deposit() {
        servo.position = depositPosition
    }

    fun intakeClocking() {
        servo.position = intakePosition
    }

    fun lift() {
        servo.position = liftPosition
    }

    fun cap() {
        servo.position = capPosition
    }
}

