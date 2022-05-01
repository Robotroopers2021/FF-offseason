package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.DeviceSubsystem

class Slides (private val servo: KServo) : DeviceSubsystem() {

    companion object SlideConstants {
        const val alliancePosition = 0.3
        const val sharedPositionExt = 0.5
        const val sharedPosition = 0.75
        const val homePosition = 0.9
        const val intakePosition = 0.3
        const val zeroPosition = 0.9
        const val intakeExtPos = 0.5
    }

    fun home() {
        servo.position = homePosition
    }

    fun alliance() {
        servo.position = alliancePosition
    }

    fun sharedExt() {
        servo.position = sharedPositionExt
    }

    fun intakePos() {
        servo.position = intakePosition
    }

    fun slidesZero() {
        servo.position = zeroPosition
    }

    fun shared() {
        servo.position = sharedPosition
    }
    fun intakeExt() {
        servo.position = intakeExtPos
    }
}

