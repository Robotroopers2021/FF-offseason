package org.firstinspires.ftc.teamcode.koawalib.subsystem

import com.asiankoala.koawalib.command.commands.InstantCommand
import com.asiankoala.koawalib.hardware.servo.KServo
import com.asiankoala.koawalib.subsystem.DeviceSubsystem
import org.firstinspires.ftc.teamcode.koawalib.subsystem.Slides.SlideConstants.alliancePosition

class Slides (private val servo: KServo) : DeviceSubsystem() {

    companion object SlideConstants {
        const val alliancePosition = 0.9
        const val sharedPosition = 0.0
        const val homePosition = 0.0
    }

    fun home() {
        servo.position = homePosition
    }

    fun alliance() {
        servo.position = alliancePosition
    }

    fun shared() {
        servo.position = sharedPosition
    }
}
